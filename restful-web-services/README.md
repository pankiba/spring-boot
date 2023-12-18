# RESTFul Web Services


## spring-boot
Study Spring Boot Features ! Happy Learning !!

## RestFul Web Services

* Representational State Transfer ( REST )
    * Platform Independent, Loosely coupled & Interoperable
    * REST mainly rests on following three -
        * Resource : Resources are the fundamental elements. A resource is an object with a type, associated data, relationships to other resources, and a set of methods that operate on it. Every resource has unique identifier on web platform, which is called as URI ( Universal Resource Identifier). In REST world, we use nouns to identify type of resource.
        * Representation
            * JSON
            * XML
        * Verbs
            * GET : **retrieve an existing resource**
            * POST : **create a new entry of resource**
            * PUT : **modify an existing resource**
            * DELETE : **remove an existing resource** 
            
#### API Specifications

- Retrieve all BusinessUnits   				- GET /businessunits
- Create BusinessUnit        				- POST /businessunits
- Retrieve BusinessUnit   					- GET /businessunits/{id}
- Update BusinessUnit						- PUT /businessunits/{id}
- Remove BusinessUnit   	 				- DELETE /businessunits/{id}

- Retrieve all employees for single BusinessUnit  		- GET /businessunits/{businessUnitId}/employees
- Create employee for BusinessUnit  					- POST /businessunits/{businessUnitId}/employees
- Retrieve details of employee for BusinessUnit			- GET /businessunits/{businessUnitId}/employees/{employeeId}

#### Internationalization

* LocaleResolver interface has implementations that determine the current locale based on the session, cookies, the Accept-Language header, or a fixed value. We are using SessionLocaleResolver with default Locale as US.

#### Swagger Documentation

* restful-web-services/v2/api-docs
* restful-web-services/swagger-ui.html

#### Actuator Monitoring

* restful-web-services/actuator/  displays all available endpoints. All actuators other than /health and /info are disabled by default

* The management.endpoints.web.exposure.include flag can be used to enable the actuators from yml/properties configurations

#### Static Filtering

* @JsonIgnore
* @JsonIgnoreProperties