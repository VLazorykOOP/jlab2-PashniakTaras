import java.util.Scanner;
import java.util.EmptyStackException;


public class Main {
    public static void main(String[] args) {
        boolean Continue = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("��� 2.\n������� ����� �������� (1-4): ");
            int choice = in.nextInt();
            switch (choice) {
                case 1: {
                    testFraction(); break;
                }
                case 2: {
                    testQuadraticEquation(); break;
                }
                case 3: {
                    testIntegerStack(); break;
                }
                default:{System.out.print("�� ��");     break;}
            }
            Scanner check = new Scanner(System.in);
            System.out.println("�������� �������� ���? 1 - ���, 2 - ͳ \n");
            int cont = check.nextInt();
            if(cont == 2) {
                Continue = false;
            } else {
                Continue = true;
            }
        } while (Continue);
        System.out.println("�����������");
    }
}

public class Fraction {
        private long wholePart;    // ֳ�� �������
        private short numerator;    // ��������� (������� �������)

        // ����������� ��� ��������� ��'���� Fraction � ����� �� �������� ���������
        public Fraction(long wholePart, short numerator) {
            this.wholePart = wholePart;
            this.numerator = numerator;
        }

        // ����� ��� ��������� ����������� ������������� �����
        public double toDecimal() {
            return wholePart + (double) numerator / 100;
        }

        // ����� ��� ��������� ���� �����
        public Fraction add(Fraction other) {
            long newWholePart = this.wholePart + other.wholePart;
            short newNumerator = (short) (this.numerator + other.numerator);

            if (newNumerator >= 100) {
                newWholePart++;
                newNumerator -= 100;
            }

            return new Fraction(newWholePart, newNumerator);
        }

        // ����� ��� �������� ���� �����
        public Fraction subtract(Fraction other) {
            long newWholePart = this.wholePart - other.wholePart;
            short newNumerator = (short) (this.numerator - other.numerator);

            if (newNumerator < 0) {
                newWholePart--;
                newNumerator += 100;
            }

            return new Fraction(newWholePart, newNumerator);
        }

        // ����� ��� �������� ���� �����
        public Fraction multiply(Fraction other) {
            long newWholePart = this.wholePart * other.wholePart;
            short newNumerator = (short) ((this.numerator * other.wholePart) + (other.numerator * this.wholePart));

            if (newNumerator >= 100) {
                long carry = newNumerator / 100;
                newWholePart += carry;
                newNumerator -= carry * 100;
            }

            return new Fraction(newWholePart, newNumerator);
        }

        // ����� ��� ��������� ���� �����
        public int compareTo(Fraction other) {
            double thisValue = this.toDecimal();
            double otherValue = other.toDecimal();

            return Double.compare(thisValue, otherValue);
        }

        // ����� ��� ��������� ����� � ������ �����
        @Override
        public String toString() {
            return wholePart + "." + String.format("%02d", numerator);
        }
}

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // ����� ��� ���������� ������������
    private double calculateDiscriminant() {
        return b * b - 4 * a * c;
    }

    // ����� ��� ���������� ������� ������
    public int getNumberOfRoots() {
        double discriminant = calculateDiscriminant();
        if (discriminant > 0) {
            return 2; // ��� ��� �����
        } else if (discriminant == 0) {
            return 1; // ���� �����
        } else {
            return 0; // ���� ������ ������
        }
    }

    // ����� ��� ���������� ������ �������
    public double[] getRoots() {
        double discriminant = calculateDiscriminant();
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{root1, root2};
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            return null; // ���� ������ ������
        }
    }

    // ����� ��� ��������� ���������� ���������� ������
    public static void printResults(int numberOfRoots, double[] solutions) {
        if (numberOfRoots == 2) {
            System.out.println("��� ��� �����: x1 = " + solutions[0] + ", x2 = " + solutions[1]);
        } else if (numberOfRoots == 1) {
            System.out.println("���� �����: x = " + solutions[0]);
        } else {
            System.out.println("���� ������ ������.");
        }
    }
}

public class IntegerStack {
    private Node top; // ������� �����

    // ���� ��� ������������� ����� �����
    private class Node {
        int data;  // �������� �����
        Node next; // ��������� �� ��������� �����

        public Node(int data) {
            this.data = data;
        }
    }

    // ��������, �� � ���� �������
    public boolean isEmpty() {
        return top == null;
    }

    // ��������� �������� �� ������� �����
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    // ��������� �������� � ������� ����� �� ���������� ���� ��������
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    // ����� �������� � ����� � ���������� ������� (1 - �� ������, 2 - ��������� � ��� ���)
    public int search(int data) {
        Node current = top;
        int position = 1;

        while (current != null) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }

        return -1; // ������� �� ��������
    }
}


    //������� ���������� �����
    public static void testFraction() {
        System.out.println("����������� ����������");

        Fraction fraction1 = new Fraction(3, (short) 50);
        Fraction fraction2 = new Fraction(2, (short) 75);

        System.out.println("��� 1: " + fraction1);
        System.out.println("��� 2: " + fraction2);

        Fraction sum = fraction1.add(fraction2);
        System.out.println("����: " + sum);

        Fraction difference = fraction1.subtract(fraction2);
        System.out.println("г�����: " + difference);

        Fraction product = fraction1.multiply(fraction2);
        System.out.println("�������: " + product);

        int comparison = fraction1.compareTo(fraction2);
        if (comparison > 0) {
            System.out.println("��� 1 ������ �� ��� 2");
        } else if (comparison < 0) {
            System.out.println("��� 2 ������ �� ��� 1");
        } else {
            System.out.println("����� ���");
        }
    }

    public static void testQuadraticEquation() {
        System.out.println("����������� ����������");

        QuadraticEquation equation1 = new QuadraticEquation(1, -3, 2);
        QuadraticEquation equation2 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation equation3 = new QuadraticEquation(1, 1, 1);

        System.out.println("\nг������ 1: a=1, b=-3, c=2");
        System.out.println("����'����:");
        int roots1 = equation1.getNumberOfRoots();
        double[] solutions1 = equation1.getRoots();
        QuadraticEquation.printResults(roots1, solutions1);

        System.out.println("\nг������ 2: a=1, b=2, c=1");
        System.out.println("����'����:");
        int roots2 = equation2.getNumberOfRoots();
        double[] solutions2 = equation2.getRoots();
        QuadraticEquation.printResults(roots2, solutions2);

        System.out.println("\nг������ 3: a=1, b=1, c=1");
        System.out.println("����'����:");
        int roots3 = equation3.getNumberOfRoots();
        double[] solutions3 = equation3.getRoots();
        QuadraticEquation.printResults(roots3, solutions3);
    }
    public static void testIntegerStack() {
        IntegerStack stack = new IntegerStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("����: 10 -> 20 -> 30");

        int popped = stack.pop();
        System.out.println("�������� � ������� �����: " + popped);
        System.out.println("���� ���� ���������: 10 -> 20");

        int position = stack.search(20);
        if (position != -1) {
            System.out.println("������� 20 ����������� �� �������: " + position);
        } else {
            System.out.println("������� 20 �� �������� � �����.");
        }
    }