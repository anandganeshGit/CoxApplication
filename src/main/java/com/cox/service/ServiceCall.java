package com.cox.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import com.cox.model.*;

public class ServiceCall {
	
	private RestTemplate restTemplate;
    private String apiCallUrl;
    private volatile Map<Integer, DealerOutput> dealerDetails = new HashMap<>();

    public ServiceCall(RestTemplate restTemplate, String apiCallUrl) {
        this.restTemplate = restTemplate;
        this.apiCallUrl = apiCallUrl;
    }

    public synchronized DataSet getDataSet() throws Exception {
    	dealerDetails.clear();
        DataSet dataSet = new DataSet();
        String dataSetId = this.getDataSetId();
        dataSet.setDatasetId(dataSetId);
       // System.out.println("THE DATASETID : "+dataSetId);
        // get vehicle ids of all vehicles in the data set
        Set<Integer> vechIds = this.getVechIds(dataSetId);
        // get vehicle details of all vehicles in the data set, then vehicle details to their respective dealerships
        vechIds.parallelStream().forEach(i -> this.getVechInfo(dataSetId, i));
        OutputToPost opToPost = new OutputToPost();
        opToPost.setDealers(dealerDetails.values());
        dataSet.setOp(opToPost);
        return dataSet;
    }

    public String getDataSetId() throws Exception {
        String getDataSetUrl = apiCallUrl + "datasetId";
      //  System.out.println("THE ENDPOINT URL IS : "+endPointUrl);
        GetDatasetIdJSON getDatasetIdJSON =  this.restTemplate.getForObject(getDataSetUrl, GetDatasetIdJSON.class);
        if (getDatasetIdJSON == null) throw new Exception("Failed to get data set id response from server");
        System.out.println("THE DATASETID IS : "+getDatasetIdJSON.getDatasetId());
        return getDatasetIdJSON.getDatasetId();
    }

    public FinalJson postFinalJson(String datasetId, OutputToPost dataSet) throws Exception {
        String endPointUrl = apiCallUrl + datasetId + "/answer";
        FinalJson finalJson = this.restTemplate.postForObject(endPointUrl, dataSet, FinalJson.class);
        if (finalJson == null) throw new Exception("Failed to post answer back to server");
        return finalJson;
    }

    public OutputToPost getCheatAnswer(String datasetId) throws Exception {
        String endPointUrl = apiCallUrl + datasetId + "/cheat";
        OutputToPost cheatop = this.restTemplate.getForObject(endPointUrl, OutputToPost.class);
        if (cheatop == null) throw new Exception("Failed to get cheat answer from server");
        return cheatop;

    }

    public DealerOutput getDealer(String datasetId, int dealerId) {
        String endPointUrl = apiCallUrl + datasetId + "/dealers/" + dealerId;
        DealerOutput dealer = this.restTemplate.getForObject(endPointUrl, DealerOutput.class);
        //dealer.setVehicles(dealerIds2Vehicles.get(dealerId));
        return dealer;
    }

    public Set<Integer> getVechIds(String datasetId) throws Exception {
        String vechIdUrl = apiCallUrl + datasetId + "/vehicles";
        GetVechileIDJSON vehicleIdsResponse = this.restTemplate.getForObject(vechIdUrl, GetVechileIDJSON.class);
        if (vehicleIdsResponse == null) throw new Exception("Failed to get dealer response from server");
        return vehicleIdsResponse.getVehicleIds();
    }

    private GetVehicleInfo getVechInfo(String datasetId, int vechId){
        String vechInfoUrl = apiCallUrl + datasetId + "/vehicles/" + vechId;
        GetVehicleInfo vechInfo = this.restTemplate.getForObject(vechInfoUrl, GetVehicleInfo.class);
        int dealerId = vechInfo.getDealerId();
        constDealDet(datasetId, dealerId, vechInfo);
        //System.out.println("Vech Info : "+vechInfo.toString());
        return vechInfo;
    }

    private void constDealDet(String datasetId, int dealerId, GetVehicleInfo getVehicleInfo){
        synchronized (dealerDetails) {
            if (!dealerDetails.containsKey(dealerId)) {
            	dealerDetails.put(dealerId, getDealer(datasetId, dealerId));
            }
            dealerDetails.get(dealerId).addVech(getVehicleInfo);
        }
    }

}
