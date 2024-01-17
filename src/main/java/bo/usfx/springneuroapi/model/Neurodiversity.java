package bo.usfx.springneuroapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "neurodiversities")
public final class Neurodiversity {
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

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getWorldWidePercentage() {
        return worldWidePercentage;
    }

    public void setWorldWidePercentage(final String worldWidePercentage) {
        this.worldWidePercentage = worldWidePercentage;
    }

    public String getBasicExplanationLink() {
        return basicExplanationLink;
    }

    public void setBasicExplanationLink(final String basicExplanationLink) {
        this.basicExplanationLink = basicExplanationLink;
    }

    public String getTestLink() {
        return testLink;
    }

    public void setTestLink(final String testLink) {
        this.testLink = testLink;
    }
}
