package dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRs {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("result")
    @Expose
    private Result result;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Counts {

        @SerializedName("cases")
        @Expose
        private Integer cases;
        @SerializedName("suites")
        @Expose
        private Integer suites;
        @SerializedName("milestones")
        @Expose
        private Integer milestones;
        @SerializedName("runs")
        @Expose
        private Runs runs;
        @SerializedName("defects")
        @Expose
        private Defects defects;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Defects {
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("open")
        @Expose
        private Integer open;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("counts")
        @Expose
        private Counts counts;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Runs {
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("active")
        @Expose
        private Integer active;
    }

}
