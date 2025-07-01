package com.eventsequor.relations_jpa.services;

import com.eventsequor.relations_jpa.entities.*;
import com.eventsequor.relations_jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class InvoiceClientService {

    @Autowired
    private IInvoiceRepository iInvoiceRepository;

    @Autowired
    private IClientRepository iClientRepository;

    @Autowired
    private IClientDetailsRepository iClientDetailsRepository;

    @Autowired
    private IStudentRepository iStudentRepository;

    @Autowired
    private ICourseRepository iCourseRepository;


    @Transactional
    public void manyToManyRemoveBidirectionalFind() {
        Optional<Student> optionalStudent = iStudentRepository.findOneWithCourses(1L);
        Optional<Student> optionalStudent2 = iStudentRepository.findOneWithCourses(2L);

        Student student1 = optionalStudent.get();
        Student student2 = optionalStudent2.get();

        Course course = iCourseRepository.findOneWithStudents(1L).get();
        Course course2 = iCourseRepository.findOneWithStudents(2L).get();

        student1.addCourses(course, course2);

        student2.addCourse(course2);

        iStudentRepository.saveAll(Set.of(student1, student2));

        System.out.println("Student 1: " + optionalStudent);
        System.out.println("Student 2: " + optionalStudent2);


        Optional<Student> optionalStudentDb = iStudentRepository.findOneWithCourses(1L);
        if (optionalStudentDb.isPresent()) {
            Student studentDb = optionalStudentDb.get();
            Optional<Course> courseOptionalDb = iCourseRepository.findOneWithStudents(1L);

            System.out.println("Student before to delete: " + studentDb);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.removeCourse(courseDb);

                System.out.println("Student deleted: " + iStudentRepository.save(studentDb));
            }
        }
    }


    @Transactional
    public void manyToManyBidirectionalFind() {
        Optional<Student> optionalStudent = iStudentRepository.findOneWithCourses(1L);
        Optional<Student> optionalStudent2 = iStudentRepository.findOneWithCourses(2L);

        Student student1 = optionalStudent.get();
        Student student2 = optionalStudent2.get();

        Course course = iCourseRepository.findOneWithStudents(1L).get();
        Course course2 = iCourseRepository.findOneWithStudents(2L).get();

        student1.addCourses(course, course2);

        student2.addCourse(course2);

        iStudentRepository.saveAll(Set.of(student1, student2));

        System.out.println("Student 1: " + optionalStudent);
        System.out.println("Student 2: " + optionalStudent2);
    }

    @Transactional
    public void manyToManyBidirectionalRemove() {
        Student student = new Student("Jano", "Pura");
        Student student2 = new Student("Elba", "Coe");

        Course course = new Course("Master in java", "Andres");
        Course course2 = new Course("Spring boot course", "Andres");

        student.addCourses(course, course2);

        student2.addCourse(course2);

        iStudentRepository.saveAll(Set.of(student, student2));

        System.out.println("Student 1: " + student);
        System.out.println("Student 2: " + student2);

        Optional<Student> optionalStudentDb = iStudentRepository.findOneWithCourses(4L);
        if (optionalStudentDb.isPresent()) {
            Student studentDb = optionalStudentDb.get();
            Optional<Course> courseOptionalDb = iCourseRepository.findOneWithStudents(3L);

            System.out.println("Student before to delete: " + studentDb);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.removeCourse(courseDb);

                System.out.println("Student deleted: " + iStudentRepository.save(studentDb));
            }
        }
    }

    @Transactional
    public void manyToManyBidirectional() {
        Student student = new Student("Jano", "Pura");
        Student student2 = new Student("Elba", "Coe");

        Course course = new Course("Master in java", "Andres");
        Course course2 = new Course("Spring boot course", "Andres");

        student.addCourses(course, course2);

        student2.addCourse(course2);

        iStudentRepository.saveAll(Set.of(student, student2));

        System.out.println("Student 1: " + student);
        System.out.println("Student 2: " + student2);
    }

    @Transactional
    public void manyToManyRemove() {
        Student student = new Student("Jano", "Pura");
        Student student2 = new Student("Elba", "Coe");

        Course course = new Course("Master in java", "Andres");
        Course course2 = new Course("Spring boot course", "Andres");

        student.setCourses(new HashSet<>(Set.of(course, course2)));
        student2.setCourses(new HashSet<>(Set.of(course)));

        iStudentRepository.saveAll(new HashSet<>(Set.of(student, student2)));

        System.out.println("Student 1: " + student);
        System.out.println("Student 2: " + student2);

        Optional<Student> optionalStudentDb = iStudentRepository.findOneWithCourses(4L);
        if (optionalStudentDb.isPresent()) {
            Student studentDb = optionalStudentDb.get();
            Optional<Course> courseOptionalDb = iCourseRepository.findById(3L);

            System.out.println("Student before to delete: " + studentDb);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.getCourses().remove(courseDb);

                System.out.println("Student deleted: " + iStudentRepository.save(studentDb));
            }
        }
    }

    @Transactional
    public void manyToManyRemoveFind() {
        Optional<Student> optionalStudent = iStudentRepository.findById(1L);
        Optional<Student> optionalStudent2 = iStudentRepository.findById(2L);

        Student student1 = optionalStudent.get();
        Student student2 = optionalStudent2.get();

        Course course = iCourseRepository.findById(1L).get();
        Course course2 = iCourseRepository.findById(2L).get();

        student1.setCourses(new HashSet<>(Set.of(course, course2)));
        student2.setCourses(new HashSet<>(Set.of(course)));

        iStudentRepository.saveAll(Set.of(student1, student2));

        System.out.println("Student 1: " + optionalStudent);
        System.out.println("Student 2: " + optionalStudent2);

        Optional<Student> optionalStudentDb = iStudentRepository.findOneWithCourses(1L);
        if (optionalStudentDb.isPresent()) {
            Student studentDb = optionalStudentDb.get();
            Optional<Course> courseOptionalDb = iCourseRepository.findById(2L);

            System.out.println("Student before to delete: " + studentDb);
            if (courseOptionalDb.isPresent()) {
                Course courseDb = courseOptionalDb.get();
                studentDb.getCourses().remove(courseDb);

                System.out.println("Student deleted: " + iStudentRepository.save(studentDb));
            }
        }
    }

    @Transactional
    public void manyToManyFind() {
        Optional<Student> optionalStudent = iStudentRepository.findById(1L);
        Optional<Student> optionalStudent2 = iStudentRepository.findById(2L);

        Student student1 = optionalStudent.get();
        Student student2 = optionalStudent2.get();

        Course course = iCourseRepository.findById(1L).get();
        Course course2 = iCourseRepository.findById(2L).get();

        student1.setCourses(new HashSet<>(Set.of(course, course2)));
        student2.setCourses(new HashSet<>(Set.of(course)));

        iStudentRepository.saveAll(Set.of(student1, student2));

        System.out.println("Student 1: " + optionalStudent);
        System.out.println("Student 2: " + optionalStudent2);
    }

    @Transactional
    public void manyToMany() {
        Student student = new Student("Jano", "Pura");
        Student student2 = new Student("Elba", "Coe");

        Course course = new Course("Master in java", "Andres");
        Course course2 = new Course("Spring boot course", "Andres");

        student.setCourses(Set.of(course, course2));
        student2.setCourses(Set.of(course));

        iStudentRepository.saveAll(Set.of(student, student2));

        System.out.println("Student 1: " + student);
        System.out.println("Student 2: " + student2);
    }


    @Transactional
    public void oneToOneBidirectionalFindById() {
        Client client = iClientRepository.findOne(1L).orElseThrow();

        ClientDetails clientDetails = new ClientDetails(true, 5000);

        client.setClientDetails(clientDetails);

        iClientRepository.save(client);

        System.out.println("Client saved: " + client);
    }

    @Transactional
    public void oneToOneBidirectional() {
        Client client = new Client("Erba", "Pura");

        ClientDetails clientDetails = new ClientDetails(true, 5000);

        client.setClientDetails(clientDetails);

        iClientRepository.save(client);

        System.out.println("Client saved: " + client);
    }

    @Transactional
    public void oneToOneFindById() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        iClientDetailsRepository.save(clientDetails);

        Optional<Client> optionalClient = iClientRepository.findOne(2L);
        optionalClient.ifPresent(client -> {
            client.setClientDetails(clientDetails);
            iClientRepository.save(client);

            System.out.println(client);
        });

    }

    @Transactional
    public void oneToOne() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        iClientDetailsRepository.save(clientDetails);

        Client client = new Client("Erba", "Pura");
        client.setClientDetails(clientDetails);
        iClientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void removeInvoiceBidirectional() {
        Client client = new Client("Fran", "Moras");
        Invoice invoice1 = new Invoice("House purchases", 5000L);
        Invoice invoice2 = new Invoice("Office purchases", 8000L);

        client.addInvoices(invoice1, invoice2);

        Client clientSave = iClientRepository.save(client);
        System.out.println("Client saved: " + clientSave);

        Optional<Client> optionalClientDb = iClientRepository.findOne(3L);
        optionalClientDb.ifPresent(clientDb -> {
            Invoice invoice3 = new Invoice("House purchases", 5000L);
            invoice3.setId(2L);
            //Optional<Invoice> invoiceOptional = iInvoiceRepository.findById(2L);
            Optional<Invoice> invoiceOptional = Optional.of(invoice3);

            invoiceOptional.ifPresent(invoice -> {
                clientDb.removeInvoice(invoice);
                System.out.println("Client deleted: " + iClientRepository.save(clientDb));
            });
        });
    }

    @Transactional
    public void removeInvoiceBidirectionalFindById() {
        Optional<Client> optionalClient = iClientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {
            Invoice invoice1 = new Invoice("House purchases", 5000L);
            Invoice invoice2 = new Invoice("Office purchases", 8000L);

            client.addInvoices(invoice1, invoice2);

            Client clientSave = iClientRepository.save(client);
            System.out.println("Client saved: " + clientSave);
        });
        Optional<Client> optionalClientDb = iClientRepository.findOne(1L);
        optionalClientDb.ifPresent(client -> {
            Invoice invoice3 = new Invoice("House purchases", 5000L);
            invoice3.setId(2L);
            //Optional<Invoice> invoiceOptional = iInvoiceRepository.findById(2L);
            Optional<Invoice> invoiceOptional = Optional.of(invoice3);

            invoiceOptional.ifPresent(invoice -> {
                client.removeInvoice(invoice);
                System.out.println("Client deleted: " + iClientRepository.save(client));
            });
        });
    }

    @Transactional
    public void oneToManyInvoiceBidirectionalFindById() {
        Optional<Client> optionalClient = iClientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {
            Invoice invoice1 = new Invoice("House purchases", 5000L);
            Invoice invoice2 = new Invoice("Office purchases", 8000L);

            client.addInvoices(invoice1, invoice2);

            Client clientSave = iClientRepository.save(client);
            System.out.println("Client saved: " + clientSave);
        });
        if (optionalClient.isEmpty())
            System.out.println();
    }

    @Transactional
    public void oneToManyInvoiceBidirectional() {
        Client client = new Client("Fran", "Moras");
        Invoice invoice1 = new Invoice("House purchases", 5000L);
        Invoice invoice2 = new Invoice("Office purchases", 8000L);

        client.addInvoices(invoice1, invoice2);

        Client clientSave = iClientRepository.save(client);
        System.out.println("Client saved: " + clientSave);


    }

    @Transactional
    public void removeAddressesFindById() {
        Optional<Client> optionalClient = iClientRepository.findById(2L);
        if (optionalClient.isEmpty())
            return;
        Client client = optionalClient.get();
        Address address1 = new Address("Poblado", 1);
        Address address2 = new Address("La y", 2);
        client.getAddresses().add(address1);
        client.getAddresses().add(address2);
        Client clientUpdated = iClientRepository.save(client);

        System.out.println("Client saved: " + client);

        Optional<Client> optionalClient1 = iClientRepository.findOne(2L);
        optionalClient1.ifPresent(c -> {
            c.getAddresses().remove(0);
            iClientRepository.save(c);
            System.out.println("Client updated: " + c);
        });
    }

    @Transactional
    public void removeAddress() {
        Client client = new Client("Juan", "Mora");
        Address address1 = new Address("Avenida hostia", 1235);
        Address address2 = new Address("Calle chida", 9875);
        client.getAddresses().add(address1);
        client.getAddresses().add(address2);
        iClientRepository.save(client);

        System.out.println(client);

        Optional<Client> optionalClient = iClientRepository.findById(3L);
        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address1);
            iClientRepository.save(c);
            System.out.println("New object: " + c);
        });
    }

    @Transactional
    public void oneToManyFindById() {
        Client client = iClientRepository.findById(2L).orElseThrow();

        System.out.println(client);
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("El poblado", 1235));
        addresses.add(new Address("Columnas", 9875));
        client.getAddresses().clear();
        client.getAddresses().addAll(addresses);
        iClientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void oneToMany() {
        Client client = new Client("Juan", "Mora");
        client.setAddresses(
                Set.of(
                        new Address("El vergel", 1235),
                        new Address("Vasco dagama", 9875)
                )
        );
        iClientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void manyToOne() {
        Client client = new Client("John", "Doe");
        iClientRepository.save(client);

        Invoice invoice = new Invoice("Office buys", 2000L);
        invoice.setClient(client);

        Invoice invoiceSaved = iInvoiceRepository.save(invoice);

        System.out.println("Invoice: \n" + invoiceSaved);
    }

    @Transactional
    public void manyToOneFindById() {
        Client client = iClientRepository.findById(1L).orElseThrow();

        Invoice invoice = new Invoice("Office purchases", 2000L);
        invoice.setClient(client);

        Invoice invoiceSaved = iInvoiceRepository.save(invoice);

        System.out.println("Invoice: \n" + invoiceSaved);
    }
}
