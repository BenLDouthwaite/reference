---
title: "A Philosophy Of Software Design : Notes"
date: "2021-10-21T22:34:00.000Z"
description: ""
---

### Study Notes

### Chapter 6: General-Purpose Modules are Deeper.

- Questions to ask yourself:
  - What is the simplest interface that will cover all my _current_ needs?
  - In how many situations will this method be used?
  - Is this API easy to use for my current needs?
  - e.g. supporting operations on an input range.
- Push specialization upwards (and downwards).
- Eliminate special cases
  - Design the normal case in a generic way, to handle edge conditions

<hr />

### Chapter 7: Different Layer, Different Abstraction.

- "If a system contains adjacent layers with similar abstractions, this suggests a problem with the class decomposition".
- 🚩 Pass-Through Method.
  - Method that forwards that only requests on, usually via the same API.
  - Indicates lack of clean division of responsibility.
  - Create dependencies between classes.
  - Refactor to provide distinct set of responsibilities per class.
- Decorator Design Pattern: extend an existing object, to provide new functionality
  - Separates special purpose and generic cases.
  - Often shallow, with minimal new functionality for a lot of boilerplate
  - Consider standalone classes to provide the same functionality, instead of a decorator
- The interfaces of a class should be different from their implementations, or the class is likely not very deep.
  - Ideally, the interface should be a simplifier view for the class to be providing value.
- Pass-Through Variables
  - Can: Attempt to incorporate to existing objects
  - Can: Use a context object, to store global state for the system
    - These bring similar problems to global state. Immutability resolves some issues.
- Layers should remove, not add complexity to a system.

<hr />

### Chapter 8: Pull Complexity Downwards.

- It's more important for a module to have a simple interface, than a simple implementation.
- Configuration Parameters
  - Save users from needing to figure out the correct values
  - Parameters can quickly become out of date, and the number can grow to be difficult to manage
  - Ask: Will users be able to figure out a better value than the developers can?
  - Provide reasonable defaults, with overrides only if needed.
  - "Each module should solve a problem completely, configuration parameters result in an incomplete solution."

<hr />

### Chapter 9: Better Together Or Better Apart?

- Goal is always to reduce complexity of the system as a whole.
- Many small compoments add complexity to manage them all, and find the desired component when using them.
  - "every new interface adds complexity"
- Indications two pieces of code are related:
  - They share information, they are used together, they overlap conceptually (under a single higher level category), it's hard to understand one without the other.
- Bring together is information is shared.
- Bring together if it will simplify the interface.
- Bring together to eliminate duplication.
  - Extracted methods should be long (enough, to justify), with a simple signature.
- Avoid Modules including code to specialise for specific use cases.
- "Special-purpose code associated with a general-purpose mechanism should normally go in a different module."
- 🚩 Repetition
- Separated class for logging is an example where the beenfit is minimal, for added complexity undestanding the interface.
- When to split methods:
  - Separating methods with 1 invocation, can introduce temporal decomposition.
  - Each method should do 1 thing, and do it completely.
  - Methods should have a simple interface, and be deep.
- 🚩 Conjoined Methods
  - "It should be possible to understand each method independently"
- Joining 2 shallow methods together is a good way to reduce complexity
- Avoid sacrificing method depth for length.
