public class Motherboard {
    private String name;
    private String componentType;
    private String chipset;
    private String socket;
    private String memoryType;
    private String maxMemory;
    private String memorySlots;
    private String memoryFreq;


    public Motherboard(String name, String componentType, String chipset, String socket, String memoryType, String maxMemory, String memorySlots, String memoryFreq) {
        this.name = name;
        this.componentType = componentType;
        this.chipset = chipset;
        this.socket = socket;
        this.memoryType = memoryType;
        this.maxMemory = maxMemory;
        this.memorySlots = memorySlots;
        this.memoryFreq = memoryFreq;
    }

    public Object[] getObject(){
        return new Object[]{name, componentType, chipset, socket, memoryType, maxMemory, memorySlots, memoryFreq};
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

    public String getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(String memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getMemoryFreq() {
        return memoryFreq;
    }

    public void setMemoryFreq(String memoryFreq) {
        this.memoryFreq = memoryFreq;
    }


    public void updateMotherboard(String name, String componentType, String chipset, String socket, String memoryType, String maxMemory, String memorySlots, String memoryFreq) {
        this.name = name;
        this.componentType = componentType;
        this.chipset = chipset;
        this.socket = socket;
        this.memoryType = memoryType;
        this.maxMemory = maxMemory;
        this.memorySlots = memorySlots;
        this.memoryFreq = memoryFreq;
    }

}
