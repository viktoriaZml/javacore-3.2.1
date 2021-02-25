import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
  private final boolean verified;
  private final String sentCount;


  public Status(@JsonProperty("verified") boolean verified
          , @JsonProperty("sentCount") String sentCount) {
    this.verified = verified;
    this.sentCount = sentCount;
  }

  public boolean isVerified() {
    return verified;
  }

  public String getSentCount() {
    return sentCount;
  }

  @Override
  public String toString() {
    return "Status{" +
            "verified=" + verified +
            ", sentCount='" + sentCount + '\'' +
            '}';
  }
}
