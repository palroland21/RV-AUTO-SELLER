package com.rv_auto_seller.dto.response;

import java.util.Map;

public class AdminStatsResponse {
    private long totalUsers;
    private long totalListings;
    private long totalAppointments;
    private long totalFeedbacks;

    private Map<String, Long> listingsByBrand;
    private Map<String, Long> listingsByType;
    private Map<String, Long> listingsByFuel;

    public long getTotalUsers() { return totalUsers; }
    public long getTotalListings() { return totalListings; }
    public long getTotalAppointments() { return totalAppointments; }
    public long getTotalFeedbacks() { return totalFeedbacks; }
    public Map<String, Long> getListingsByBrand() { return listingsByBrand; }
    public Map<String, Long> getListingsByType() { return listingsByType; }
    public Map<String, Long> getListingsByFuel() { return listingsByFuel; }

    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }
    public void setTotalListings(long totalListings) { this.totalListings = totalListings; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }
    public void setTotalFeedbacks(long totalFeedbacks) { this.totalFeedbacks = totalFeedbacks; }
    public void setListingsByBrand(Map<String, Long> listingsByBrand) { this.listingsByBrand = listingsByBrand; }
    public void setListingsByType(Map<String, Long> listingsByType) { this.listingsByType = listingsByType; }
    public void setListingsByFuel(Map<String, Long> listingsByFuel) { this.listingsByFuel = listingsByFuel; }
}
