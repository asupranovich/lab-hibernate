@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(name = "getStudentWithAllData", query = "select s from Student s join fetch s.courses join fetch s.addresses where s.id = :id")
})

package com.itechart.students.schedule.model;
