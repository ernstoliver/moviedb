package com.reaktorlabs.model;

/**
 *
 * @author ernst
 */
public class Actor {
    
    private String name;
    private String character;
    private String profile_pic;
    
    public Actor() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    @Override
    public String toString() {
        return "Actor{" + "nam=" + name + ", character=" + character + ", profile_pic=" + profile_pic + '}';
    }
}
