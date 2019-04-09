package ee.taltech.iti0202.computershop.computershop;

import ee.taltech.iti0202.computershop.component.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerShop {
    public enum UseCase {
        WORKSTATION,
        GAMING,
        UNDEFINED
    }
    private static final double GAMING_GPU_MULTIPLIER = 1.3;
    private static final double WORKSTATION_CPU_MULTIPLIER = 1.3;
    private List<CPU> CPUs = new ArrayList<>();
    private List<GPU> GPUs = new ArrayList<>();
    private List<MoBo> MoBos = new ArrayList<>();
    private List<PSU> PSUs = new ArrayList<>();
    private List<Storage> Storages = new ArrayList<>();
    private List<RAM> RAMs = new ArrayList<>();
    private String name;

    public ComputerShop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * If you want to have your eyes still see light, do not proceed.
     *
     * @param budget client's budget
     * @param useCase client's usecase
     * @return list of components used
     */
    public List<Component> buildComputer (double budget, UseCase useCase) {
        List<Component> potentialBuild = new ArrayList<>();
        double cost = Double.MAX_VALUE;
        int performancePoints = 0;
        // okay
        for(PSU psu : PSUs) {
            // i guess it's still ok
            for(MoBo mobo : MoBos) {
                // uhm, getting a bit bad here
                for(GPU gpu : GPUs.stream()
                        .filter(x -> x.getConnector().equals(mobo.getGpuConnector()))
                        .collect(Collectors.toList())) {
                    // hey what are you doing
                    for(CPU cpu : CPUs.stream()
                            .filter(x -> x.getConnector().equals(mobo.getCpuConnector()))
                            .collect(Collectors.toList())) {
                        // what is wrong with you, stop
                        for(Storage storage : Storages.stream()
                                .filter(x -> x.getConnector().equals(mobo.getStorageConnector()))
                                .collect(Collectors.toList())) {
                            // STOP, STOP STOOOPPPP
                            for(RAM ram : RAMs.stream()
                                    .filter(x -> x.getType().equals(mobo.getRamType()))
                                    .collect(Collectors.toList())) {
                                List<Component> tempBuild = new ArrayList<>(List.of(psu, mobo, gpu, cpu, storage, ram));
                                // oh no
                                if(tempBuild.stream()
                                        .mapToInt(Component::getPowerConsumption)
                                        .sum() <= 0) {
                                    double tempCost = tempBuild.stream()
                                            .mapToDouble(Component::getPrice)
                                            .sum();
                                    // MORE?
                                    if(tempCost <= budget) {
                                        int tempPerformancePoints = tempBuild.stream()
                                                .mapToInt(Component::getPerformacePoints)
                                                .sum();
                                        // screw you guys, I'm going home
                                        if(useCase.equals(UseCase.GAMING)) {
                                            tempPerformancePoints += gpu.getPerformacePoints()
                                                    * (GAMING_GPU_MULTIPLIER - 1);
                                        }
                                        else if(useCase.equals(UseCase.WORKSTATION)) {
                                            tempPerformancePoints += cpu.getPerformacePoints()
                                                    * (WORKSTATION_CPU_MULTIPLIER - 1);
                                        }
                                        if(tempPerformancePoints > performancePoints
                                                || tempPerformancePoints >= performancePoints && tempCost < cost) {
                                            potentialBuild = List.copyOf(tempBuild);
                                            cost = tempCost;
                                            performancePoints = tempPerformancePoints;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(potentialBuild.size() == 0) {
            System.out.println("No suitable build found, give us more money.");
            return potentialBuild;
        } else {
            System.out.println(String.format("Found computer with %d performance points with cost of %s, "
                    + "these are the components used:", performancePoints, cost));
            for(Component component : potentialBuild) {
                System.out.println(component);
            }
            return potentialBuild;
        }
    }

    public void addComponent(Component component) {

        switch (component.getComponentType()) {
            case MoBo:
                MoBos.add((MoBo) component);
                break;
            case CPU:
                CPUs.add((CPU) component);
                break;
            case GPU:
                GPUs.add((GPU) component);
                break;
            case PSU:
                PSUs.add((PSU) component);
                break;
            case RAM:
                RAMs.add((RAM) component);
                break;
            case Storage:
                Storages.add((Storage) component);
        }
    }

    public void removeComponent(Component component) {
        switch (component.getComponentType()) {
            case MoBo:
                MoBos.remove((MoBo) component);
                break;
            case CPU:
                CPUs.remove((CPU) component);
                break;
            case GPU:
                GPUs.remove((GPU) component);
                break;
            case PSU:
                PSUs.remove((PSU) component);
                break;
            case RAM:
                RAMs.remove((RAM) component);
                break;
            case Storage:
                Storages.remove((Storage) component);
        }
    }
}
