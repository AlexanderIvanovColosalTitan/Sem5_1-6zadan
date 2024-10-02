import java.util.Scanner;

interface HomeAppliance {
    void operate();
}

interface Refrigerator extends HomeAppliance {
    void setTemperature(int temperature);
}

interface WashingMachine extends HomeAppliance {
    void startWash();
}

interface Microwave extends HomeAppliance {
    void setPower(int power);
}


class SamsungRefrigerator implements Refrigerator {
    public void setTemperature(int temperature) {
        System.out.println("Температура холодильника Samsung установлена на " + temperature + " градусов");
    }

    public void operate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите температуру: ");
        int temperature = scanner.nextInt();
        setTemperature(temperature);
    }
}

class LGWashingMachine implements WashingMachine {
    private int waterLevel;
    private int washTime;

    public void startWash() {
        System.out.println("Стиральная машина LG запущена");
        System.out.println("Уровень воды: " + waterLevel);
        System.out.println("Время стирки: " + washTime + " минут");
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setWashTime(int washTime) {
        this.washTime = washTime;
    }

    public void operate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите уровень воды: ");
        int waterLevel = scanner.nextInt();
        setWaterLevel(waterLevel);

        System.out.print("Введите время стирки: ");
        int washTime = scanner.nextInt();
        setWashTime(washTime);

        startWash();
    }
}


class PanasonicMicrowave implements Microwave {
    public void setPower(int power) {
        System.out.println("Мощность микроволновой печи Panasonic установлена на " + power + " ватт");
    }

    public void operate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите мощность: ");
        int power = scanner.nextInt();
        setPower(power);
    }
}

interface HomeApplianceFactory {
    Refrigerator createRefrigerator();
    WashingMachine createWashingMachine();
    Microwave createMicrowave();
}

class SamsungFactory implements HomeApplianceFactory {
    public Refrigerator createRefrigerator() {
        return new SamsungRefrigerator();
    }

    public WashingMachine createWashingMachine() {
        return new LGWashingMachine();
    }

    public Microwave createMicrowave() {
        return new PanasonicMicrowave();
    }
}

class LGFactory implements HomeApplianceFactory {
    public Refrigerator createRefrigerator() {
        return new SamsungRefrigerator();
    }

    public WashingMachine createWashingMachine() {
        return new LGWashingMachine();
    }

    public Microwave createMicrowave() {
        return new PanasonicMicrowave();
    }
}
