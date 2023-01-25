public class Products {
    private String name;
    private String componentType;
    private String chipset;
    private String socket;
    private String memoryType;
    private String maxMemory;
    private String memorySlots;
    private String memoryFreq;
    private boolean modularity;
    private String memoryEffectiveFreq;
    private String series;
    private String numberOfCores;
    private String freq;
    private String power;
    private String capacity;
    private String ssdType;
    private String maxRead;
    private String maxWrite;
    private String memorySize;
    private String coolingSystem;
    Processor processor;
    Motherboard motherboard;
    Memory memory;
    Ssd ssd;
    Gpu gpu;
    Psu psu;

    public void createProcessor( String name, String componentType, String chipset, String socket, String memoryType, String maxMemory, String memoryFreq, String series, String numberOfCores, String freq, String power) {
        processor = new Processor(name, componentType, chipset, socket, memoryType, maxMemory, memoryFreq, series, numberOfCores, freq, power);
    }
    public void createMotherboard(String name, String componentType, String chipset, String socket, String memoryType, String maxMemory, String memorySlots, String memoryFreq) {
        motherboard = new Motherboard(name, componentType, chipset, socket, memoryType, maxMemory, memorySlots, memoryFreq);
    }

    public void createMemory(String name, String componentType, String memoryType, String series, String freq, String capacity) {
        memory = new Memory(name, componentType, memoryType, series, freq, capacity);
    }

    public void createSSD(String name, String componentType, String series, String capacity, String ssdType, String maxRead, String maxWrite) {
        ssd = new Ssd(name, componentType, series, capacity, ssdType, maxRead, maxWrite);
    }

    public void createGpu(String name, String componentType, String chipset, String memoryType, String memoryEffectiveFreq, String series, String power, String memorySize, String coolingSystem) {
        gpu = new Gpu(name, componentType, chipset, memoryType, memoryEffectiveFreq, series, power, memorySize, coolingSystem);
    }

    public void createPsu(String name, String componentType, boolean modularity,  String power) {
        psu = new Psu(name, componentType, modularity, power);
    }
}
