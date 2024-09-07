import java.util.ArrayList;
import java.util.List;

public class Ex8 {

    abstract static class Animal {
        public abstract String makeSound();
    }

    static class Dog extends Animal {
        @Override
        public String makeSound() {
            return "Woof!";
        }
    }

    static class Cat extends Animal {
        @Override
        public String makeSound() {
            return "Meow!";
        }
    }

    static class Labrador extends Dog {
        @Override
        public String makeSound() {
            return "Bark! I`m labrador";
        }
    }

    static class AnimalShelter {
        private List<Dog> dogs = new ArrayList<>();
        private List<Animal> otherAnimals = new ArrayList<>();

        public void addAnimals(Animal animal) {
            if (animal instanceof Dog) {
                dogs.add((Dog) animal);
            } else {
                otherAnimals.add(animal);
            }
        }

        public void printAnimalSounds() {
            System.out.println("Dogs sounds:");
            for (Dog dog : dogs) {
                System.out.println(dog.makeSound());
            }
            System.out.println("Other animals sounds:");
            for (Animal animal : otherAnimals) {
                System.out.println(animal.makeSound());
            }
        }
    }
     public static void main(String[] args){
        Dog dog = new Dog();
        Cat cat = new Cat();
        Labrador labrador = new Labrador();

        AnimalShelter shelter = new AnimalShelter();
        shelter.addAnimals(dog);
        shelter.addAnimals(cat);
        shelter.addAnimals(labrador);

        shelter.printAnimalSounds();
     }

}
