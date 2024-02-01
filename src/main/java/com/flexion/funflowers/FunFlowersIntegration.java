package com.flexion.funflowers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
public class FunFlowersIntegration implements Integration {

    private static final String BASE_URL = "https://recruitment-task.flexionmobile.com/javachallenge/rest";
    String developerId;
    private final HttpClient httpClient = HttpClient.newHttpClient();


    FunFlowersIntegration(String developerId) {
        this.developerId = developerId;
    }

    ;

    @Override
    public Purchase
    buy(String itemId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/developer/" + developerId + "/buy/" + itemId))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parsePurchaseResponse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private Purchase parsePurchaseResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FlexionPurchase purchaseMap = mapper.readValue(jsonResponse, FlexionPurchase.class);
            return purchaseMap;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Purchase> getPurchases() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/developer/" + developerId + "/all"))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parsePurchaseListResponse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Purchase> parsePurchaseListResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, Map.class);

            List<FlexionPurchase> flexionPurchases = mapper.convertValue(responseMap.get("purchases"),
                    new TypeReference<List<FlexionPurchase>>() {});

            List<Purchase> purchases = new ArrayList<>(flexionPurchases);
            return purchases;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse purchase list response: " + jsonResponse, e);
        }
    }

    @Override
    public void consume(Purchase purchase) {
        Objects.requireNonNull(purchase);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/developer/" + developerId + "/consume/" + purchase.getId()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

