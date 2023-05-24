package models.claim;

import enums.ClaimStatus;
import enums.ClaimType;

import java.util.UUID;


public class Claim {

    private String id;
    private ClaimType type;
    private ClaimStatus status;
    private String parentId;
    private String message;

    public Claim(ClaimType type, ClaimStatus status, String parentId, String message) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.status = status;
        this.parentId = parentId;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public ClaimType getType() {
        return type;
    }

    public String getParentId() {
        return parentId;
    }

    public String getMessage() {
        return message;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
}
