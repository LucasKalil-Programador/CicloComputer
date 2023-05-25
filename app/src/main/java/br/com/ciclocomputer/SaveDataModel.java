package br.com.ciclocomputer;

public class SaveDataModel {

    private String AvgSpeedText;
    private String TimeText;
    private String DistanceText;

    public SaveDataModel(String avgSpeedText, String timeText, String distanceText) {
        AvgSpeedText = avgSpeedText;
        TimeText = timeText;
        DistanceText = distanceText;
    }

    public SaveDataModel() {
    }

    public SaveDataModel(SaveDataModel onSaveClick) {
    }

    public String getAvgSpeedText() {
        return AvgSpeedText;
    }

    public void setAvgSpeedText(String avgSpeedText) {
        AvgSpeedText = avgSpeedText;
    }

    public String getTimeText() {
        return TimeText;
    }

    public void setTimeText(String timeText) {
        TimeText = timeText;
    }

    public String getDistanceText() {
        return DistanceText;
    }

    public void setDistanceText(String distanceText) {
        DistanceText = distanceText;
    }
}
