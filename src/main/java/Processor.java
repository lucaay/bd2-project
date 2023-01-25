public class Processor {
    private String name;
    private String componentType;
    private String chipset;
    private String socket;
    private String memoryType;
    private String maxMemory;
    private String memoryFreq;
    private String series;
    private String numberOfCores;
    private String freq;
    private String power;
    public Processor(String name, String componentType, String chipset, String socket, String memoryType, String maxMemory, String memoryFreq, String series, String numberOfCores, String freq, String power) {
        this.name = name;
        this.componentType = componentType;
        this.chipset = chipset;
        this.socket = socket;
        this.memoryType = memoryType;
        this.maxMemory = maxMemory;
        this.memoryFreq = memoryFreq;
        this.series = series;
        this.numberOfCores = numberOfCores;
        this.freq = freq;
        this.power = power;
    }
    public Object[] processorObject(){
        return new Object[]{name, componentType, chipset, socket, memoryType, maxMemory, memoryFreq, series, numberOfCores, freq, power};
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getMemoryFreq() {
        return memoryFreq;
    }

    public void setMemoryFreq(String memoryFreq) {
        this.memoryFreq = memoryFreq;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(String numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

}
