package by.epamtc.devices.launch;

import by.epamtc.devices.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

    private static final String REMOVE_PARAMETER_TYPE = "\\w+=";
    public static final String DEVICE_TYPE_DELIMITER = ":";
    public static final String PARAMETER_DELIMITER = ",";

    private static final String FILE_NAME = "appliances_db.txt";


    public static List<Device> findDevices(String deviceType, String parameterType, String parameterValue) {

        List<Device> devices = new ArrayList<>();

        String parameter = parameterType + "=" + parameterValue;
        List<String> suitableEntries = findSuitable(deviceType, parameter);

        for (String entry : suitableEntries) {

            String[] params = deviceParameters(entry, deviceType);
            Device device = createDevices(params, deviceType);

            if (device != null) {
                devices.add(device);
            }
        }

        return devices;
    }

    private static List<String> findSuitable(String type, String parameter) {

        List<String> fileContents = readFile();

        List<String> suitable = new ArrayList<>();

        for (String s : fileContents) {
            if (!s.startsWith(type)) {
                continue;
            }
            if (s.contains(parameter)) {
                suitable.add(s);
            }
        }

        return suitable;
    }


    private static String[] deviceParameters(String entry, String type) {

        String replacement = type + DEVICE_TYPE_DELIMITER;

        entry = entry.replaceAll(" ", "");
        entry = entry.replaceAll(replacement, "");

        String[] parameters = entry.split(PARAMETER_DELIMITER);

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].replaceAll(REMOVE_PARAMETER_TYPE, "");
        }

        return parameters;
    }


    private static Device createDevices(String[] deviceParams, String deviceType) {

        switch (deviceType) {
            case "Laptop":
                return createLaptop(deviceParams);
            case "Oven":
                return createOven(deviceParams);
            case "Refrigerator":
                return createRefrigerator(deviceParams);
            case "Speakers":
                return createSpeakers(deviceParams);
            case "TabletPC":
                return createTablet(deviceParams);
            case "VacuumCleaner":
                return createCleaner(deviceParams);
        }

        return null;
    }


    private static Laptop createLaptop(String[] params) {

        Laptop laptop = new Laptop();
        laptop.setBatteryCapacity(Double.parseDouble(params[0]));
        laptop.setOC(params[1]);
        laptop.setMemoryROM(Integer.parseInt(params[2]));
        laptop.setSystemMemory(Integer.parseInt(params[3]));
        laptop.setCpu(Double.parseDouble(params[4]));
        laptop.setDisplayInches(Integer.parseInt(params[5]));

        return laptop;
    }


    private static Oven createOven(String[] params) {

        Oven oven = new Oven();
        oven.setPowerConsumption(Integer.parseInt(params[0]));
        oven.setWeight(Integer.parseInt(params[1]));
        oven.setCapacity(Integer.parseInt(params[2]));
        oven.setDepth(Integer.parseInt(params[3]));
        oven.setHeight(Double.parseDouble(params[4]));
        oven.setWidth(Double.parseDouble(params[5]));

        return oven;
    }


    private static Refrigerator createRefrigerator(String[] params) {

        Refrigerator refrigerator = new Refrigerator();
        refrigerator.setPowerConsumption(Integer.parseInt(params[0]));
        refrigerator.setWeight(Integer.parseInt(params[1]));
        refrigerator.setFreezerCapacity(Integer.parseInt(params[2]));
        refrigerator.setOverallCapacity(Double.parseDouble(params[3]));
        refrigerator.setHeight(Integer.parseInt(params[4]));
        refrigerator.setWidth(Integer.parseInt(params[5]));

        return refrigerator;
    }


    private static Speakers createSpeakers(String[] params) {

        Speakers speakers = new Speakers();
        speakers.setPowerConsumption(Integer.parseInt(params[0]));
        speakers.setNumOfSpeakers(Integer.parseInt(params[1]));
        speakers.setFrequencyRange(params[2]);
        speakers.setCordLength(Integer.parseInt(params[3]));

        return speakers;
    }


    private static TabletPC createTablet(String[] params) {

        TabletPC tabletPC = new TabletPC();
        tabletPC.setBatteryCapacity(Integer.parseInt(params[0]));
        tabletPC.setDisplayInches(Integer.parseInt(params[1]));
        tabletPC.setMemoryROM(Integer.parseInt(params[2]));
        tabletPC.setFlashMemoryCapacity(Integer.parseInt(params[3]));
        tabletPC.setColor(params[4]);

        return tabletPC;
    }

    private static VacuumCleaner createCleaner(String[] params) {

        VacuumCleaner cleaner = new VacuumCleaner();
        cleaner.setPowerConsumption(Integer.parseInt(params[0]));
        cleaner.setFilterType(params[1]);
        cleaner.setBagType(params[2]);
        cleaner.setWandType(params[3]);
        cleaner.setMotorSpeedRegulation(Integer.parseInt(params[4]));
        cleaner.setCleaningWidth(Integer.parseInt(params[5]));

        return cleaner;
    }

    private static List<String> readFile() {

        String separator = File.separator;
        String path = "src" + separator + "by" + separator + "epamtc" +
                separator + "devices" + separator + "resources" + separator + FILE_NAME;
        File file = new File(path);

        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }
}