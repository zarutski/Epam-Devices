package by.epamtc.devices;

import by.epamtc.devices.launch.Util;
import by.epamtc.devices.model.Device;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Device> devices = Util.findDevices("Oven", "CAPACITY", "33");
        for (Device m : devices) {
            System.out.println(m);
        }

        devices = Util.findDevices("VacuumCleaner", "WAND_TYPE", "all-in-one");
        for (Device m : devices) {
            System.out.println(m);
        }
    }
}
