public class Gpu {
    private String name;
    private String componentType;
    private String chipset;
    private String memoryType;
    private String memoryEffectiveFreq;
    private String series;
    private String power;
    private String memorySize;
    private String coolingSystem;

    public Gpu(String name, String componentType, String chipset, String memoryType, String memoryEffectiveFreq, String series, String power, String memorySize, String coolingSystem) {
        this.name = name;
        this.componentType = componentType;
        this.chipset = chipset;
        this.memoryType = memoryType;
        this.memoryEffectiveFreq = memoryEffectiveFreq;
        this.series = series;
        this.power = power;
        this.memorySize = memorySize;
        this.coolingSystem = coolingSystem;
    }

    public Object[] getObject(){
        return new Object[]{name, componentType, chipset, memoryType, memoryEffectiveFreq, series, power, memorySize, coolingSystem};
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

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getMemoryEffectiveFreq() {
        return memoryEffectiveFreq;
    }

    public void setMemoryEffectiveFreq(String memoryEffectiveFreq) {
        this.memoryEffectiveFreq = memoryEffectiveFreq;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    public void setCoolingSystem(String coolingSystem) {
        this.coolingSystem = coolingSystem;
    }

    public void updateGpu(String name, String componentType, String chipset, String memoryType, String memoryEffectiveFreq, String series, String power, String memorySize, String coolingSystem) {
        this.name = name;
        this.componentType = componentType;
        this.chipset = chipset;
        this.memoryType = memoryType;
        this.memoryEffectiveFreq = memoryEffectiveFreq;
        this.series = series;
        this.power = power;
        this.memorySize = memorySize;
        this.coolingSystem = coolingSystem;
    }
}
