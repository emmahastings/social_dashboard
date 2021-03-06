package com.hastings.dashboard.model;

/**
 * Created by emmakhastings on 17/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to hold Twitter follower details
 */
public class TwitterFollower {

    private String name;

    private String screenName;

    private String location;

    private String description;

    private String profileUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
