package dataStructureAlgo;

class Equipment {
    String name;
    String quantity;

    public Equipment(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

class EquipmentNode {
    Equipment data;
    EquipmentNode next;

    public EquipmentNode(Equipment data) {
        this.data = data;
        this.next = null;
    }
}

class EquipmentLinkedList {
    EquipmentNode head;

    public EquipmentLinkedList() {
        head = null;
    }

    public void addEquipment(String name, String quantity) {
        Equipment newEquipment = new Equipment(name, quantity);
        EquipmentNode newNode = new EquipmentNode(newEquipment);

        if (head == null) {
            head = newNode;
        } else {
            EquipmentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void displayEquipment() {
        EquipmentNode current = head;
        while (current != null) {
            System.out.println("Equipment: " + current.data.name + ", Quantity: " + current.data.quantity);
            current = current.next;
        }
    }
}
