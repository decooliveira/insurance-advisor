
## Appendix

**Other components**

**AdvisorController**

The rest controller of the application. It receives an PersonalInformation object as input and then returns a RiskProfile object as response.

**RiskCalculator**

A service class of the business layer. It converts the PersonalInformation object received as input into a InsurableData entity. Then it delegates the rules processing the risk profile building to another component called RiskProfileBuilder.

**Insurance**

It’s an interface that represents a generic type with a single operation “getScore(). This type is implemented by concrete classes such as AutoInsurance, HomeInsurance, DisabilityInsurance and LifeInsurance.

In order to add new lines of insurances we only need to implement Insurance interface.

**Insurable**

It’s an interface that represents a generic type of items that can have insurance such as Vehicle, House and so on. In future, new items only need to implement this interface, so we can have items like airplanes, boats and others.

**InsurableData**

It’s a generic type of insurable data. It’s implemented by a concrete class called InsuranceData. In future this class should be segregated.

**InsuranceData**

It holds together the data of our current insurable items such as Vehicle, User and House. New insurable items could be added to this class. As an improvement this class should be segregated when new items will be added to the tool.

**RiskProfileBuilder**

This class is responsible to build the risk profile object that will be sent as response to user. It uses the InsuranceFactory in order to get the insurances and then set each of them to the RiskProfile object.

**InsuranceFactory**

Although it is a factory, it delegates the building of insurance objects to calculator objects. As a strategy pattern, it uses the CalculatorFactory which builds the appropriate insurance objects.

**CalculatorFactory**

This factory returns calculator objects using strategy pattern. This way, each line of insurance area able to calculate and build their own insurance objects with correct scores.

This strategy pattern makes simple adding new strategies for new insurance lines to the advisor tool

**Rules objects**

All the insurance rules are represented by "Rule Objects". The goal is to achieve some level of code reuse since any service can instantiate any given rule and execute it.
However, in a real application should be more abstract and less coupled
  
