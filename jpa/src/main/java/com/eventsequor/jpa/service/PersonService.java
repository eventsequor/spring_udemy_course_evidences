package com.eventsequor.jpa.service;

import com.eventsequor.jpa.dto.PersonDTO;
import com.eventsequor.jpa.entities.Person;
import com.eventsequor.jpa.repositories.IPersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

    private final IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void whereIn() {
        System.out.println("========= Query where in =========");
        print(personRepository.getPersonByIds(1L, 2L, 5L));
    }

    @Transactional(readOnly = true)
    public void subQueries() {
        System.out.println("====== Query by shorter name =======");
        List<Object[]> records = personRepository.getShorterName();
        records.forEach(r -> {
            System.out.println("Name: " + r[0] + " - Length: " + r[1]);
        });

        System.out.println("===== Last record in Person table =======");
        Optional<Person> optionalPerson = personRepository.getLastRecord();
        optionalPerson.ifPresent(System.out::println);
    }

    @Transactional
    public void queryFunctionAggregation() {
        Long count = personRepository.getTotalPersons();
        Long minId = personRepository.getMinId();
        Long maxId = personRepository.getMaxId();
        System.out.println("Query with total of record on the table: " + count);
        System.out.println("Query with min id in table person: " + minId);
        System.out.println("Query with max id in table person: " + maxId);

        List<Object[]> records = personRepository.getPersonNameLength();
        records.forEach(r -> {
            String name = (String) r[0];
            Integer length = (Integer) r[1];
            System.out.println("Name: " + name + " - length: " + length);
        });

        System.out.println("\n ");
        System.out.println("Shorter name: " + personRepository.getMinLengthName());
        System.out.println("Longer name: " + personRepository.getMaxLengthName());

        System.out.println("\n============= Queries aggregation functions ===============");
        Object[] resume = (Object[]) personRepository.getResumeAggregationFunctions();
        System.out.println("min: " + resume[0]);
        System.out.println("max: " + resume[1]);
        System.out.println("sum: " + resume[2]);
        System.out.println("average length of names: " + resume[3]);
        System.out.println("count: " + resume[4]);
    }

    @Transactional
    public void personalizeQueriesBetween() {
        List<Person> personList;
        System.out.println("========= Query by ranges by id ===========");
        print(personRepository.findAllBetweenId(2L, 5L));

        System.out.println("========= Query by ranges by name ===========");
        print(personRepository.findAllBetweenName("J", "P"));

        System.out.println("Second implementation");
        System.out.println("========= Query by ranges by id ===========");
        print(personRepository.findByIdBetweenOrderByIdDesc(2L, 5L));

        System.out.println("Second implementation");

        System.out.println("========= Query by ranges by name ===========");
        print(personRepository.findByNameBetweenOrderByNameAscLastname("J", "P"));

        System.out.println("========= Query by ranges get all ===========");
        print(personRepository.getAllOrder());

        System.out.println("========= Query by ranges find all by ===========");
        print(personRepository.findAllByOrderByNameAscLastnameDesc());
    }

    private void print(List<Person> personList) {
        personList.forEach(System.out::println);
    }

    @Transactional
    public void personalizeQueriesConcatUpperAndLowerCase() {
        List<String> names;
        System.out.println("========= Query all names and lastnames ==========");
        names = personRepository.findAllFullNameConcat();
        names.forEach(System.out::println);

        System.out.println("========= Query all names and lastnames in upper case ==========");
        names = personRepository.findAllFullNameConcatUpper();
        names.forEach(System.out::println);

        System.out.println("========= Query all names and lastnames in lower case ==========");
        names = personRepository.findAllFullNameConcatLower();
        names.forEach(System.out::println);

        System.out.println("========= Query all names and lastnames mix upper and lower case ==========");
        List<Object[]> personList = personRepository.finAllPersonsMixUpperLower();
        personList.forEach(p -> System.out.println("Name: " + p[0] + " - Lastname: " + p[1]));
    }

    @Transactional(readOnly = true)
    public void personalizeQueriesDistinct() {
        System.out.println("====== Query with name of persons =========");
        List<String> names = personRepository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("====== Query with name of persons and distinct =========");
        List<String> names2 = personRepository.findAllNamesDistinct();
        names2.forEach(System.out::println);

        System.out.println("====== Query with name of persons and distinct =========");
        List<String> programmingLanguages = personRepository.findAllProgrammingLanguageDistinct();
        programmingLanguages.forEach(System.out::println);

        System.out.println("====== Query with name of persons and distinct =========");
        Long numberOfProgrammingLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
        System.out.println("Number of programming language that exist: " + numberOfProgrammingLanguage);
    }

    @Transactional
    public void personalizeQueries2() {
        System.out.println("============== Query by object type person ===============");
        List<Object[]> personRegis = personRepository.findAllMixPerson();
        personRegis.forEach(reg -> System.out.println("Programming language: " + reg[1] + " \nPerson: " + reg[0]));
        System.out.println("\n=============== Query which return object with a custom instance ===============");
        List<Person> personList = personRepository.findAllObjectPersonalizePerson();
        personList.forEach(System.out::println);

        System.out.println("============= query that use a person DTO =============");
        List<PersonDTO> personDTOList = personRepository.findAllPersonDTO();
        personDTOList.forEach(System.out::println);
    }

    @Transactional
    public void personalizeQuery() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============= QUERY THE NAME BY ID ==============");
        System.out.println("Type the id to query:");
        Long id = Long.parseLong(scanner.nextLine());
        scanner.close();
        String name = personRepository.getFullNameById(id);
        System.out.println("The name is: " + name);

        System.out.println("\n Custom query by id:");
        Object[] customDates = (Object[]) personRepository.getPersonValuesById(id);
        String values = Stream.of(customDates)
                .map(o -> {
                    if (o instanceof Long)
                        return ((Long) o).toString();
                    if (o instanceof String)
                        return o.toString();
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" - "));
        System.out.println(values);
    }

    @Transactional
    public void deleteUsingObject() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the id to eliminate");
        Long id = Long.parseLong(scanner.nextLine());
        Optional<Person> optionalPerson = personRepository.findById(id);
        optionalPerson.ifPresentOrElse(personRepository::delete, () -> System.out.println("The person with id " + id + " does not exist"));
        personRepository.findAll().forEach(System.out::println);
        scanner.close();
    }

    @Transactional
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the id to eliminate");
        Long id = Long.parseLong(scanner.nextLine());
        personRepository.deleteById(id);

        personRepository.findAll().forEach(System.out::println);
        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the id of person to update:");
        Long id = Long.parseLong(scanner.nextLine());
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            System.out.println("Type the new programing language");
            String programmingLanguage = scanner.nextLine();
            optionalPerson.get().setProgrammingLanguage(programmingLanguage);
            Person personSaved = personRepository.save(optionalPerson.get());
            System.out.println("Person updated: \n" + personSaved);
        }
        if (optionalPerson.isEmpty())
            throw new RuntimeException("The person with id " + id + " does not exist");

        scanner.close();
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please type the name:");
        String name = scanner.nextLine();
        System.out.println("Please type the lastname:");
        String lastname = scanner.nextLine();
        System.out.println("Please type the programming language:");
        String programmingLanguage = scanner.nextLine();


        Person person = new Person(name, lastname, programmingLanguage);
        Person personCreated = personRepository.save(person);

        Person personInDataBase = personRepository.findById(personCreated.getId()).orElse(null);
        System.out.println(personInDataBase == null ? "Person were not create successfully" : "Person created successfully: \n" + personInDataBase);
    }

    @Transactional(readOnly = true)
    public void list() {
        //List<Person> personList = (List<Person>) personRepository.findAll();
        //List<Person> personList = (List<Person>) personRepository.findByProgrammingLanguage("Java");
        //List<Person> personList = (List<Person>) personRepository.customFindByProgrammingLanguage("Java", "Andres");
        List<Person> personList = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andres");

        personList.forEach(System.out::println);

        List<Object[]> personValues = personRepository.getPersonValues();
        personValues.forEach(obj -> System.out.println(obj[0] + " expert in: " + obj[1]));

        System.out.println("From here only one data");

        List<Object[]> personName = personRepository.getPersonValues("Andres");
        personName.stream().map(obj -> obj[0]).forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findOne() {
        Person person = personRepository.findByNameContaining("An").orElse(null);
        System.out.println(person);
    }

    @Transactional(readOnly = true)
    public void list2() {
        //List<Person> personList = (List<Person>) personRepository.findAll();
        //List<Person> personList = (List<Person>) personRepository.findByProgrammingLanguage("Java");
        //List<Person> personList = (List<Person>) personRepository.customFindByProgrammingLanguage("Java", "Andres");
        List<Person> personList = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andres");

        personList.forEach(System.out::println);

        List<Object[]> personValues = personRepository.getPersonValues();
        personValues.forEach(obj -> System.out.println(obj[0] + " expert in: " + obj[1]));

        System.out.println("From here only one data");

        List<Object[]> personName = personRepository.getPersonValues("Andres");
        personName.stream().map(obj -> obj[0]).forEach(System.out::println);
    }

}
