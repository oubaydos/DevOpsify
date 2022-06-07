const getArchetypes = (setArchetypes) => {
  setArchetypes([
    {
      groupId: "org.apache.maven.archetypes",
      artifactId: "maven-archetype-quickstart",
      description: "An archetype which contains a sample Maven project",
    },
    {
      //org.springframework.boot:spring-boot-sample-data-jpa-archetype (Spring Boot Data JPA Sample)

      groupId: "org.springframework.boot",
      artifactId: "spring-boot-sample-data-jpa-archetype",
      description: "Spring Boot Data JPA Sample",
    },
    {
      //nl.delphinity:springboot (A basic starter template using springboot, jpa data, thymeleaf and MVC)
      groupId: "nl.delphinity",
      artifactId: "springboot",
      description:
        "A basic starter template using springboot, jpa data, thymeleaf and MVC",
    },
    {
      //jp.blackawa:spring-bootstrapping-archetype (Bootstrap Spring Boot project with Spring Security Database Authentication/Authorization.)
      groupId: "jp.blackawa",
      artifactId: "spring-bootstrapping-archetype",
      description:
        "Bootstrap Spring Boot project with Spring Security Database Authentication/Authorization",
    },
  ]);
};

export default getArchetypes;
