public class Ssd {
    private String name;
    private String componentType;
    private String series;
    private String capacity;
    private String ssdType;
    private String maxRead;
    private String maxWrite;

    public Ssd(String name, String componentType, String series, String capacity, String ssdType, String maxRead, String maxWrite) {
        this.name = name;
        this.componentType = componentType;
        this.series = series;
        this.capacity = capacity;
        this.ssdType = ssdType;
        this.maxRead = maxRead;
        this.maxWrite = maxWrite;
    }

    public Object[] ssdObject(){
        return new Object[]{name, componentType, series, capacity, ssdType, maxRead, maxWrite};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSsdType() {
        return ssdType;
    }

    public void setSsdType(String ssdType) {
        this.ssdType = ssdType;
    }

    public String getMaxRead() {
        return maxRead;
    }

    public void setMaxRead(String maxRead) {
        this.maxRead = maxRead;
    }

    public String getMaxWrite() {
        return maxWrite;
    }

    public void setMaxWrite(String maxWrite) {
        this.maxWrite = maxWrite;
    }

    public void updateSsd(String name, String componentType, String series, String capacity, String ssdType, String maxRead, String maxWrite){
        this.name = name;
        this.componentType = componentType;
        this.series = series;
        this.capacity = capacity;
        this.ssdType = ssdType;
        this.maxRead = maxRead;
        this.maxWrite = maxWrite;
    }
}
