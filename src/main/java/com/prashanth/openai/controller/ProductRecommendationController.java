package com.prashanth.openai.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prashanth.openai.service.ProductConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductRecommendationController {

    private final ProductConfigurationService productConfigurationService;

    public ProductRecommendationController(ProductConfigurationService productConfigurationService) {
        this.productConfigurationService = productConfigurationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<ProductRecommendationResponse> generateStructuredResponse(
            @RequestBody ProductRecommendationRequest request) {

        ProductRecommendationResponse response = productConfigurationService
                .generateStructuredResponse(request.getTopic(), request.getBudget(), request.getCategory());

        return ResponseEntity.ok(response);
    }

    // Request DTO
    public static class ProductRecommendationRequest {
        @JsonProperty("topic")
        private String topic;

        @JsonProperty("budget")
        private String budget;

        @JsonProperty("category")
        private String category;

        // Constructors, getters, setters
        public ProductRecommendationRequest() {}

        public String getTopic() { return topic; }
        public void setTopic(String topic) { this.topic = topic; }

        public String getBudget() { return budget; }
        public void setBudget(String budget) { this.budget = budget; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
    }

    // Response DTO matching the markdown template structure
    public static class ProductRecommendationResponse {
        @JsonProperty("summary")
        private String summary;

        @JsonProperty("recommendations")
        private java.util.List<Recommendation> recommendations;

        @JsonProperty("budget_analysis")
        private BudgetAnalysis budgetAnalysis;

        // Constructors, getters, setters
        public ProductRecommendationResponse() {}

        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }

        public java.util.List<Recommendation> getRecommendations() { return recommendations; }
        public void setRecommendations(java.util.List<Recommendation> recommendations) {
            this.recommendations = recommendations;
        }

        public BudgetAnalysis getBudgetAnalysis() { return budgetAnalysis; }
        public void setBudgetAnalysis(BudgetAnalysis budgetAnalysis) { this.budgetAnalysis = budgetAnalysis; }
    }

    public static class Recommendation {
        @JsonProperty("name")
        private String name;

        @JsonProperty("price")
        private String price;

        @JsonProperty("description")
        private String description;

        @JsonProperty("pros")
        private java.util.List<String> pros;

        @JsonProperty("cons")
        private java.util.List<String> cons;

        // Constructors, getters, setters
        public Recommendation() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public java.util.List<String> getPros() { return pros; }
        public void setPros(java.util.List<String> pros) { this.pros = pros; }

        public java.util.List<String> getCons() { return cons; }
        public void setCons(java.util.List<String> cons) { this.cons = cons; }
    }

    public static class BudgetAnalysis {
        @JsonProperty("total_estimated_cost")
        private String totalEstimatedCost;

        @JsonProperty("cost_breakdown")
        private String costBreakdown;

        @JsonProperty("savings_tips")
        private java.util.List<String> savingsTips;

        // Constructors, getters, setters
        public BudgetAnalysis() {}

        public String getTotalEstimatedCost() { return totalEstimatedCost; }
        public void setTotalEstimatedCost(String totalEstimatedCost) {
            this.totalEstimatedCost = totalEstimatedCost;
        }

        public String getCostBreakdown() { return costBreakdown; }
        public void setCostBreakdown(String costBreakdown) { this.costBreakdown = costBreakdown; }

        public java.util.List<String> getSavingsTips() { return savingsTips; }
        public void setSavingsTips(java.util.List<String> savingsTips) { this.savingsTips = savingsTips; }
    }
}

