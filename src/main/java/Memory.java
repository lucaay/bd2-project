public class Memory {
    private String name;
    private String componentType;
    private String memoryType;
    private String series;
    private String freq;
    private String capacity;

    public  Memory(String name, String componentType, String memoryType, String series, String freq, String capacity) {
        this.name = name;
        this.componentType = componentType;
        this.memoryType = memoryType;
        this.series = series;
        this.freq = freq;
        this.capacity = capacity;
    }

    public Object[] memoryObject(){
        return new Object[]{name, componentType, memoryType, series, freq, capacity};
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

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    
}
