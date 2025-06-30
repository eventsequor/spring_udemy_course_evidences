package com.eventsequor.jpa.repositories;

import com.eventsequor.jpa.dto.PersonDTO;
import com.eventsequor.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person, Long> {


    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.name, ' ',p.lastname) as fullName from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> getPersonValues();

    @Query("select p.name, p.programmingLanguage from Person p where name=?1")
    List<Object[]> getPersonValues(String name);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where id=?1")
    Object getPersonValuesById(Long id);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and name=?2")
    List<Object[]> getPersonValues(String programmingLanguage, String name);

    @Query("select p from Person p where p.programmingLanguage=?1 and name=?2")
    List<Person> customFindByProgrammingLanguage(String programmingLanguage, String name);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();


    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonalizePerson();

    @Query("select new com.eventsequor.jpa.dto.PersonDTO(p.name, p.lastname) from Person p")
    List<PersonDTO> findAllPersonDTO();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("select p.name || ' ' || p.lastname as fullName from Person p")
    List<String> findAllFullNameConcat();

    @Query("select upper(p.name || ' ' || p.lastname) as fullName from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(p.name || ' ' || p.lastname) as fullName from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select upper(p.name), lower(p.programmingLanguage) from Person p")
    List<Object[]> finAllPersonsMixUpperLower();

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name asc, p.lastname asc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastname asc")
    List<Person> findAllBetweenName(String c1, String c2);

    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameAscLastname(String name1, String name2);

    @Query("select p from Person p order by p.name desc, p.lastname asc")
    List<Person> getAllOrder();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    @Query("select count(p) from Person p")
    Long getTotalPersons();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLength();

    @Query("select max(length(p.name)) from Person p")
    Integer getMaxLengthName();

    @Query("select min(length(p.name)) from Person p")
    Integer getMinLengthName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    Object getResumeAggregationFunctions();

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select max(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select p from Person p where p.id = (select max(p.id) from Person p)")
    public Optional<Person> getLastRecord();

    @Query("select p from Person p where p.id not in ?1")
    List<Person> getPersonByIds(Long... ids);


}
