package bo.usfx.springneuroapi.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "neurodiversities")
public class Neurodiversity {
    @Id
    private String id;
    private String name;
    private String description;
    private String worldWidePercentage;
    private String basicExplanationLink;
    private String testLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorldWidePercentage() {
        return worldWidePercentage;
    }

    public void setWorldWidePercentage(String worldWidePercentage) {
        this.worldWidePercentage = worldWidePercentage;
    }

    public String getBasicExplanationLink() {
        return basicExplanationLink;
    }

    public void setBasicExplanationLink(String basicExplanationLink) {
        this.basicExplanationLink = basicExplanationLink;
    }

    public String getTestLink() {
        return testLink;
    }

    public void setTestLink(String testLink) {
        this.testLink = testLink;
    }
}
