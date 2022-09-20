# advent-of-code-2019

## Day 1
Overview: Calculating fuel requirements for launching modules and then for launching modules and their fuel

Pretty straight forward, basic recursion used.

## Day 2
Introduction to this year's IntComputer, currently this day is not using the common implementation. Covered Add, Multiply and Terminate.

## Day 3
Calculating wire crossing points

Notable use of Record introduced in Java 14

## Day 4
Counting possible passwords

Pretty easy, probably could have used recursion but depth was fixed at 6 and not dynamic so decided not to.

## Day 5
Second use of IntComputer, covered inputs, outputs, jump if true, jump if false, less than, equals and the parameter modes of position mode and immediate mode.

This started my reusable IntComputer implementation. Struggled to understand how the inputs/outputs worked for a bit.

## Day 6
Creating path of orbits to Santa

Implemented a custom tree data structure to calculate the distance.

## Day 7
Amplifiers that leveraged IntComputers

More IntComputers, being a bean it's a singleton, so I allowed it to pass in/out an IntComputer context for multiple logical IntComputers. This also required dynamically adding inputs/outputs while the computer was running. Later decided to make IntComputer just a concrete class and not a bean.

Notable use of Guava's Collections2.permutations() for generating all permutations of input phase settings.

## Day 8
Image flattening

Notable use of Guava's HashMultiset for counting color frequency.

## Day 9
More IntComputer action including relative parameter mode and requirements for BigIntegers

Cut some corners here, would be nice to dynamically size the memory addresses based on the address being accessed or written to but I just hardcoded at something sufficiently large for now.

## Day 10
Checking for counts of visible asteroids 

Notable use of Math.atan2 for converting between points and radial degrees, tried a custom implementation for Part 1 but then moved to leverage Math.atan2 when implementing part 2. 

## Day 11
More IntComputer for painting registration number on panels.

Nothing too exciting, the solution to part 1 was very well aligned to part 2.

## Day 12
Easy part 1, needed a hint for part 2, found this comment on Reddit that got me to the answer
> Note that the different dimensions are independent of each other. X's don't depend on y's and z's.

Notable use of LCM for lowest common multiple from Apache's commons-math3.