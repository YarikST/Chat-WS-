package WS.MyClass;

import WS.Klient;



public class ConnectEvent {
    private String name;
    private Klient.KlientKey klientKey;

    public ConnectEvent(String name, Klient.KlientKey klientKey) {
        this.name = name;
        this.klientKey = klientKey;
    }

    public String getName() {
        return name;
    }

    public Klient.KlientKey getKlientKey() {
        return klientKey;
    }
}
