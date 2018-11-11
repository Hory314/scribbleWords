package pl.hory.Entity;

public class Word
{
    private Integer id;
    private String word;
    private String addDate;
    private String adderIp;
    private String accepted;
    private String rejectReason;
    private String reviewDate;

    public Word()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getAddDate()
    {
        return addDate;
    }

    public void setAddDate(String addDate)
    {
        this.addDate = addDate;
    }

    public String getAdderIp()
    {
        return adderIp;
    }

    public void setAdderIp(String adderIp)
    {
        this.adderIp = adderIp;
    }

    public String getAccepted()
    {
        return accepted;
    }

    public void setAccepted(String accepted)
    {
        this.accepted = accepted;
    }

    public String getRejectReason()
    {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason)
    {
        this.rejectReason = rejectReason;
    }

    public String getReviewDate()
    {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate)
    {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString()
    {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", addDate='" + addDate + '\'' +
                ", adderIp='" + adderIp + '\'' +
                ", accepted='" + accepted + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                '}';
    }
}
