package net.sf.companymanager.objectmapper;

public class TargetBean {
    private String fullName;
    private Boolean oldEnoughToVote;

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the oldEnoughToVote
     */
    public Boolean getOldEnoughToVote() {
        return oldEnoughToVote;
    }

    /**
     * @param oldEnoughToVote the oldEnoughToVote to set
     */
    public void setOldEnoughToVote(final Boolean oldEnoughToVote) {
        this.oldEnoughToVote = oldEnoughToVote;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TargetBean [fullName=" + fullName + ", oldEnoughToVote=" + oldEnoughToVote + "]";
    }

}
