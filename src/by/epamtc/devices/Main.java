package by.epamtc.devices;

import by.epamtc.devices.launch.Util;
import by.epamtc.devices.model.Device;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Device> master = Util.findDevices("Oven", "CAPACITY", "33");
        for (Device m : master) {
            System.out.println(m);
        }

        master = Util.findDevices("VacuumCleaner", "WAND_TYPE", "all-in-one");
        for (Device m : master) {
            System.out.println(m);
        }
    }
}
