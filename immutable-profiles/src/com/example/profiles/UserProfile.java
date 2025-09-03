package com.example.profiles;

/**
 * Mutable and confusing. Multiple constructors + setters.
 */

 // rule1: making all private and final for immutability
public final class UserProfile {
    private final String id;
    private final String email;
    private final String phone;
    private final String displayName;
    private final String address;
    private final boolean marketingOptIn;
    private final String twitter;
    private final String github;

    private UserProfile(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phone = builder.phone;
        this.displayName = builder.displayName;
        this.address = builder.address;
        this.marketingOptIn = builder.marketingOptIn;
        this.twitter = builder.twitter;
        this.github = builder.github;
    }
    // creating builder method
    public static Builder builder(String id, String email) {
        return new Builder(id, email);
    }

    public static class Builder {
    private final String id;     // Make required fields final
    private final String email;  // Make required fields final
    private String phone;
    private String displayName;
    private String address;
    private boolean marketingOptIn;
    private String twitter;
    private String github;

    private Builder(String id, String email) {
        this.id = id;
        this.email = email;
    }
    public Builder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Builder displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Builder address(String address) {
        this.address = address;
        return this;
    }

    public Builder marketingOptIn(boolean marketingOptIn) {
        this.marketingOptIn = marketingOptIn;
        return this;
    }

    public Builder twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public Builder github(String github) {
        this.github = github;
        return this;
    }

    public UserProfile build() {
        return new UserProfile(this);
    }
}


    // many setters — mutability leaks // rule2: remove setters
    // public void setId(String id) { this.id = id; }
    // public void setEmail(String email) { this.email = email; }
    // public void setPhone(String phone) { this.phone = phone; }
    // public void setDisplayName(String displayName) { this.displayName = displayName; }
    // public void setAddress(String address) { this.address = address; }
    // public void setMarketingOptIn(boolean marketingOptIn) { this.marketingOptIn = marketingOptIn; }
    // public void setTwitter(String twitter) { this.twitter = twitter; }
    // public void setGithub(String github) { this.github = github; }

    // getters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDisplayName() { return displayName; }
    public String getAddress() { return address; }
    public boolean isMarketingOptIn() { return marketingOptIn; }
    public String getTwitter() { return twitter; }
    public String getGithub() { return github; }
}
