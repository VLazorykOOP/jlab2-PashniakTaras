import java.util.Scanner;
import java.util.EmptyStackException;


public class Main {
    public static void main(String[] args) {
        boolean Continue = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Лаб 2.\nВиберіть номер завдання (1-4): ");
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
                default:{System.out.print("Не те…");     break;}
            }
            Scanner check = new Scanner(System.in);
            System.out.println("Дивимося завдання далі? 1 - Так, 2 - Ні \n");
            int cont = check.nextInt();
            if(cont == 2) {
                Continue = false;
            } else {
                Continue = true;
            }
        } while (Continue);
        System.out.println("Дапабачення");
    }
}

public class Fraction {
        private long wholePart;    // Ціла частина
        private short numerator;    // Чисельник (дробова частина)

        // Конструктор для створення об'єкта Fraction з цілою та дробовою частинами
        public Fraction(long wholePart, short numerator) {
            this.wholePart = wholePart;
            this.numerator = numerator;
        }

        // Метод для отримання десяткового представлення дробу
        public double toDecimal() {
            return wholePart + (double) numerator / 100;
        }

        // Метод для додавання двох дробів
        public Fraction add(Fraction other) {
            long newWholePart = this.wholePart + other.wholePart;
            short newNumerator = (short) (this.numerator + other.numerator);

            if (newNumerator >= 100) {
                newWholePart++;
                newNumerator -= 100;
            }

            return new Fraction(newWholePart, newNumerator);
        }

        // Метод для віднімання двох дробів
        public Fraction subtract(Fraction other) {
            long newWholePart = this.wholePart - other.wholePart;
            short newNumerator = (short) (this.numerator - other.numerator);

            if (newNumerator < 0) {
                newWholePart--;
                newNumerator += 100;
            }

            return new Fraction(newWholePart, newNumerator);
        }

        // Метод для множення двох дробів
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

        // Метод для порівняння двох дробів
        public int compareTo(Fraction other) {
            double thisValue = this.toDecimal();
            double otherValue = other.toDecimal();

            return Double.compare(thisValue, otherValue);
        }

        // Метод для виведення дробу у вигляді рядка
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

    // Метод для обчислення дискримінанту
    private double calculateDiscriminant() {
        return b * b - 4 * a * c;
    }

    // Метод для обчислення кількості коренів
    public int getNumberOfRoots() {
        double discriminant = calculateDiscriminant();
        if (discriminant > 0) {
            return 2; // Два різні корені
        } else if (discriminant == 0) {
            return 1; // Один корінь
        } else {
            return 0; // Немає дійсних коренів
        }
    }

    // Метод для обчислення коренів рівняння
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
            return null; // Немає дійсних коренів
        }
    }

    // Метод для виведення результатів обчислення коренів
    public static void printResults(int numberOfRoots, double[] solutions) {
        if (numberOfRoots == 2) {
            System.out.println("Два різні корені: x1 = " + solutions[0] + ", x2 = " + solutions[1]);
        } else if (numberOfRoots == 1) {
            System.out.println("Один корінь: x = " + solutions[0]);
        } else {
            System.out.println("Немає дійсних коренів.");
        }
    }
}

public class IntegerStack {
    private Node top; // Вершина стеку

    // Клас для представлення вузла стеку
    private class Node {
        int data;  // Значення вузла
        Node next; // Посилання на наступний вузол

        public Node(int data) {
            this.data = data;
        }
    }

    // Перевірка, чи є стек порожнім
    public boolean isEmpty() {
        return top == null;
    }

    // Додавання елемента на вершину стеку
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    // Вилучення елемента з вершини стеку та повернення його значення
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    // Пошук елемента у стеку і повернення позиції (1 - на вершині, 2 - наступний і так далі)
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

        return -1; // Елемент не знайдено
    }
}


    //Функції тестування класів
    public static void testFraction() {
        System.out.println("АВТОМАТИЧНЕ ТЕСТУВАННЯ");

        Fraction fraction1 = new Fraction(3, (short) 50);
        Fraction fraction2 = new Fraction(2, (short) 75);

        System.out.println("Дріб 1: " + fraction1);
        System.out.println("Дріб 2: " + fraction2);

        Fraction sum = fraction1.add(fraction2);
        System.out.println("Сума: " + sum);

        Fraction difference = fraction1.subtract(fraction2);
        System.out.println("Різниця: " + difference);

        Fraction product = fraction1.multiply(fraction2);
        System.out.println("Добуток: " + product);

        int comparison = fraction1.compareTo(fraction2);
        if (comparison > 0) {
            System.out.println("Дріб 1 більший за дріб 2");
        } else if (comparison < 0) {
            System.out.println("Дріб 2 більший за дріб 1");
        } else {
            System.out.println("Дроби рівні");
        }
    }

    public static void testQuadraticEquation() {
        System.out.println("АВТОМАТИЧНЕ ТЕСТУВАННЯ");

        QuadraticEquation equation1 = new QuadraticEquation(1, -3, 2);
        QuadraticEquation equation2 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation equation3 = new QuadraticEquation(1, 1, 1);

        System.out.println("\nРівняння 1: a=1, b=-3, c=2");
        System.out.println("Розв'язок:");
        int roots1 = equation1.getNumberOfRoots();
        double[] solutions1 = equation1.getRoots();
        QuadraticEquation.printResults(roots1, solutions1);

        System.out.println("\nРівняння 2: a=1, b=2, c=1");
        System.out.println("Розв'язок:");
        int roots2 = equation2.getNumberOfRoots();
        double[] solutions2 = equation2.getRoots();
        QuadraticEquation.printResults(roots2, solutions2);

        System.out.println("\nРівняння 3: a=1, b=1, c=1");
        System.out.println("Розв'язок:");
        int roots3 = equation3.getNumberOfRoots();
        double[] solutions3 = equation3.getRoots();
        QuadraticEquation.printResults(roots3, solutions3);
    }
    public static void testIntegerStack() {
        IntegerStack stack = new IntegerStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Стек: 10 -> 20 -> 30");

        int popped = stack.pop();
        System.out.println("Вилучено з вершини стеку: " + popped);
        System.out.println("Стек після вилучення: 10 -> 20");

        int position = stack.search(20);
        if (position != -1) {
            System.out.println("Елемент 20 знаходиться на позиції: " + position);
        } else {
            System.out.println("Елемент 20 не знайдено в стеці.");
        }
    }