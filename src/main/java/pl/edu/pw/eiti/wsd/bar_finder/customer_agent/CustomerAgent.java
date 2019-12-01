package pl.edu.pw.eiti.wsd.bar_finder.customer_agent;

import jade.core.Agent;

public class CustomerAgent extends Agent {

    protected void setup() {
        System.out.println("Hello World! My name is "+getLocalName());
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
