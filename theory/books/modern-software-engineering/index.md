# Modern Software Engineering - Dave Farley

## Part 1 : what is software engineering.

### 1. Introduction

- Characterise, hypothesise, predict, experiment
- Learning: Iteration, feedback, incrementalism, experimentalism, empiricism
- Managing complexity: modularity, cohesion, separation of concerns, abstraction, loose coupling
- [ ] Fred Brooks - No silver bullet https://bit.ly/2UalM4T
- [ ] Accelerate book

### 2. What is engineering?

- Much of our design process is taken from physical products, and their efficiencies that don't apply.
- When building physical things, iteration is difficult.
- Even if design is perfect, production issues arise, e.g. PS5 supply
- We are a technical scientific discipline, that often ignore the scientific method.
- [ ] Formal methods - read an introduction
- We can test our products, not a model or approximation of the product
- We want to analyse and _understand_ errors
- Fail gracefully
- Engineering is the entire process, not just the output of some code.
- 'Craft' works to a limit. A single human could realistically never produce a modern phone. 
- [ ] How fast is real time : https://bit.ly/2Lb7pL1
- [ ] Continuous integration testing at google: https://bit.ly/3eLbAgB
- [ ] **Increase the precision of our error measurements**
- Accurate measurement instead of waiting for circumstance

### 3. Fundamentals of an engineering approach.

- [ ] Cake mix analogy : https://bit.ly/3g02cWO
- [ ] Cannot measure productivity: https://bit.ly/3mDO2fB
- [ ] Read state of devops reports: https://cloud.google.com/devops/
- Stability is measured by:
	- Change failure rate: The rate at which a change introduces a defect at a particular point in the process
	- Recovery failure time: How long to recover from a failure at a particular point in the process
- Throughput is measured by:
	- Lead Time: Time from idea to deployed working software
	- Frequency: How often are changes deployed

- Stability reflects quality of what is delivered, throuput is ability to deliver. Neither confirm you're testing the right ideas.
- Apparently data shows having a change approval board does not improve the stability of software.
- [ ] Can we measure stability and throughput based on the above criteria?
- Think about tradeoffs of manual testing. Is it improving stability / reliability.
- **2 key skills: Experts at learning, managing complexity**
- Assume our ideas are wrong, and work based on that assumption. Try to prove so.

## Part 2 : Learning

### 4. Working Iteratively

- 'tracer bullets'
- Agile manifesto
- Work from an assumption of being wrong. Finding alternatives, not confirming bias.
- Smaller scope of focus, small tickets are completed quicker. Keep fewer ideas in your head at once. 
- How can we work towards continuous delivery?
- Reduction in complexity.
- Change gets more expensive as time goes on -> Can we invert that
- [ ] Mythical man month
- [ ] R] Crystal development methodology?
- Most ideas produce zero or negative value.
- Must be willing to try things, and take risks.
- Limiting blast radius: Experiments, feature toggles. 
- What is the scope of our work, given metric? Basically infinite. Need to break down smaller goals.
- > there is, at some level, the capacity of a human brain to hold the detail of the entire process
- Learning is key, but learnings must be shared and acted upon. How to log the _failed_ ideas.
- [ ] The beginning of infinity.
- Abstraction and modularity

### 5. Feedback

- > guesswork, hierarchy and tradition are more widely used for decision making
- Evidence for decisions i.e. Data-driven
- Clarity of the goal, at the right level of abstraction. 
- Define outcome not approach in tickets. 
- Testing environemnts to allow validation.
- [ ] How to best handle unreliable / non-deterministic 3rd party?
- TDD is the quickest step of iteration to get feedback, 1 line of change gets feedback or not.
- The act of writing a test from scratch, starting from a non error case calling the method under test, forces minimal data input as errors are resolved
	- How often do we copy existing tests and amend them, how much does this reduce our _understanding_ of the system
- Checking assumptions of the current behaviour of the system, before making any changes
	- Had a developer that could not run their code locally. Got confused, existing config is master was broken. That should have been fixed first. ( Which is what I did, gold star for Benny boy)
- [ ] Golang unit tests are _rapid_ talking tens of milliseconds. For some reason Java / Junit in IntelliJ can be really slow. ( relatively) Is there any way we can speed these up? Better developer experience, and compounding returns
- [ ] Trigger unit-tests as a pre-commit hook. -> A little slower, but easy way to catch issues and can be running while you start the next test.
- Using branches isolates changes, which is not integrating them together
	- CI created to avoid merge-hell (didn't know this)
	- Small branches = quicker integration. No branches = no integration needed...
- CI will ensure code is always releasable. Using feature toggles can provide safety
- TDD forces better interface design
- Testability and deployability become important
- Aim to deploy at least once per hour.
- [ ] How does this work in the apps world, with timed releases?
- Get feedback as early as possible = fail fast
- Imagine web dev before google analytics
- "inspect and adapt"
- [ ] Google DORA group

### 6. Incrementalism.

- Focus on modularity.
- [ ] How to balance overhead of services, compared to benefit of modularity
	- [ ] How does this apply to lambdas
- 'Small teams' , not 10..
	- Optimum = 8 or fewer.
	- Cross functional teams, end up only really working with a few, but still have the overhead
- Focus on autonomy.
	- [ ] Where are we lacking? Devops / Infea.
		- Product direction is fairly top-down.
- How to make small increments?
	- [ ] Working effectively with legacy code. 
- Version control. Commit early and commit often
	- Test commit revert. 
- Limit the impact of change. 
- Make code _deletable_ as well as extensible.
- Ports and adapters:
	- Very nice to make changes to kotlin code when it's easy to provide 'default beahviour'
- [ ] Hexagonal architecture
- Reducing time to find an issue is essentially reducing the impact of an issue. 
- Within TDD, top down or bottom up?
- Make progress in the face of ignorance. 
- Organise to make change easier, not handle anything that might happen. 
- 'Deep interfaces'

### 7. Empiricism.

- Focus on real value.
- Skeptical approach.
- When issues arise, stop to reivew the _facts_
	- 5 whys, get to the cause not the symptom.
- When creating tickets, particularly bugs. Focus on the behaviour that is wanted / expected. Let the developer understand how / why.
- Being organised will bring the value
- Data structures do actually matter for performance, obviously, but is rarely talked about in our daily work. Design takes priority, and new features over performance. 
	- Also reliant on downstream software.

### 8. Being Experimental.

- Have no respect whatsoever for authority.
- Data, rather than persuasion.
- People rise to the quality of their environments, provide the right tools, and people will use it.
- Test coverage is a useless goal.
	- But also pragmatism of teams. If people are cheating the system, that's a management problem.
- Deployment automation + infrastructure as code. Remove the human element. 
- Brute forcing answers to problems is arguably a form of experiment, It's based on measuring the validity of an attempt.
- TDD is more experimental than writing tests after changing the code. 
	- Use high level acceptance tests to drive lower level unit tests.
- Tests act as a body of knowledge for the code.
- Write tests assertions first.

## Part 3 : Optimise for managing complexity

### 9. Modularity.

- Can components be recombined
- CI check for method length, or parameter count
	- Parameter count happens in Sonarqube, and it's often a pain in the ass where you're adding a small change
		- But maybe that's the point, each change is small and eventually it becomes too much
	- I prefer the 'deep interfaces' approach
- Should be understandable in isolation.
- Limit scope of access to variables
	- Modules have an 'outside' and 'inside'
- Limiting blast radius
- **"treat the boundaries between our modules as opportunities to enhance our systems rather than liabilities that prevent us from changing them?"**
- [ ] Read extreme programming explained.
- Red, Green, Refactor.
- Scientific approach - standardise variables.
- Ports and adapters approach.
- Version management of services becomes important, backwards compatibility
- Scaling as a problem of having more people able to work on software, rather than handling more users. 
- Bringing together information is expensive. 
- [ ] The mythical man month
- **"So if we want our organizations to be able to scale up, the secret is to build teams and systems that need to coordinate to the minimum possible degree, we need to decouple them."**
	- Coulping within the apps team. Almost all app changes require some backend change. Tightly coupled.

### 10. Cohesion.

- "Cohesion (in computer science) is defined as "the degree to which the elements inside a module belong together."
- Fewer variables in play at once. 
	- Test: When using the debugger in IntelliJ, with a breakpoint. How many variables are in scope at that point?
- [ ] Domain driven design
- [ ] How to compiler optimisers work?
- Coupling: Given two lines of code, A and B, they are coupled when B must change behavior only because A changed.
- Cohesion: They are cohesive when a change to A allows B to change so that both add new value.
- TDD forces you to define the interface first
	- If already defined, to _understand_ it first.
- "There is a simple, subjective way to spot poor cohesion. If you have ever read a piece of code and thought “I don’t know what this code does,” it is probably because the cohesion is poor."


- [ ] Do more reading on coupling and cohesion .
	- [ ] Try and find examples in the code. 

### 11. Separation of concerns.

- Reduce coupling, increase cohesion, improve modularity.
- What changes could we emulate to test separation of concerns?
- Keep focus small
- Dependency injection is a key to simplicity, easier tests
- What are the best data structures to be placing
- "There is no silver bullet"-Fred Brooks
- Focus on separation of accidental and essential complexity
- DDD
- Keep a low tolerance for complexity
- Ports and adapters: also hexagonal architecture
- Keep level of abstraction consistent within the same level of scope
- Each service or module should have its own view of the world 
- Focus on the variables were can control
- Maintain a consistent level of abstraction throughout

### 12. Information hiding and abstraction.

- Taking time to setup a more effective environment is almost immediately worth the returns.
- There is no trade off between speed and quality.
- Error code that is ready to change, error code that is ready to delete
- Avoid future proofing or yagni
- Get the smartest people to help simplify the systems
- Business level module returns exceptions is breaking abstraction
- Could we replace or API with another language?
- Protect from 3rd party code
- 
### 13. Managing Coupling

- Interdependance
- Prefer loose to tight coupling
- Microservices - are they actually coupled anyway for us?
	- Independantly deployable is key.
	- uni-directional dependencies.
- The apps suffer becuase they need to be deployed together.
- Micro-frontends were a way around this?
- DOn't share code between microservices. 
- > it is important to avoid the need to test services together later in the process


## Part 4 - Tools

### Tools of an engineering discipline

- We need to test code. Code review is not enough
- Fast efficient feedback is ideal
- "Are we solving the right problem?"
- "Does or solution work was we think?"
- "What is the quality of our work?"
- "Are we working efficiently?"
- Easily testable code is higher quality
- Code with many measurement points is easily testable
- Adopting a test mindset is a cultural challenge
- Releasable Vs Deployable
- We should always have an independently Deployable piece of software
- Time to get a commit Deployable should be sub 1hr. What about review?

### The Modern Software Engineer

- Modularity, cohesion, separation of concerns, feedback, experimentation
- Optimize everything
- Programming is hard, so take a structured approach. Be scientific
- Organisations / Companies are themselves systems, were can apply the same concepts