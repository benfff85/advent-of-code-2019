# advent-of-code-2019

## Day 1

Overview: Calculating fuel requirements for launching modules and then for launching modules and their fuel

Pretty straight forward, basic recursion used.

## Day 2

Introduction to this year's IntComputer, currently this day is not using the common implementation. Covered Add,
Multiply and Terminate.

## Day 3

Calculating wire crossing points

Notable use of Record introduced in Java 14

## Day 4

Counting possible passwords

Pretty easy, probably could have used recursion but depth was fixed at 6 and not dynamic so decided not to.

## Day 5

Second use of IntComputer, covered inputs, outputs, jump if true, jump if false, less than, equals and the parameter
modes of position mode and immediate mode.

This started my reusable IntComputer implementation. Struggled to understand how the inputs/outputs worked for a bit.

## Day 6

Creating path of orbits to Santa

Implemented a custom tree data structure to calculate the distance.

## Day 7

Amplifiers that leveraged IntComputers

More IntComputers, being a bean it's a singleton, so I allowed it to pass in/out an IntComputer context for multiple
logical IntComputers. This also required dynamically adding inputs/outputs while the computer was running. Later decided
to make IntComputer just a concrete class and not a bean.

Notable use of Guava's Collections2.permutations() for generating all permutations of input phase settings.

## Day 8

Image flattening

Notable use of Guava's HashMultiset for counting color frequency.

## Day 9

More IntComputer action including relative parameter mode and requirements for BigIntegers

Cut some corners here, would be nice to dynamically size the memory addresses based on the address being accessed or
written to but I just hardcoded at something sufficiently large for now.

## Day 10

Checking for counts of visible asteroids

Notable use of Math.atan2 for converting between points and radial degrees, tried a custom implementation for Part 1 but
then moved to leverage Math.atan2 when implementing part 2.

## Day 11

More IntComputer for painting registration number on panels.

Nothing too exciting, the solution to part 1 was very well aligned to part 2.

## Day 12

Easy part 1, needed a hint for part 2, found this comment on Reddit that got me to the answer
> Note that the different dimensions are independent of each other. X's don't depend on y's and z's.

Notable use of LCM for lowest common multiple from Apache's commons-math3.

## Day 13

IntComputer Arcade for pong-ling game.

Interesting to see the IntComputer do something so complex. Instructions for the joystick were a bit hard to grasp at
first but once it was printed it was clear. At 6,469 moves automating the joystick was a must.

## Day 14

Chemical reactions to turn ORE into FUEL

Good fit for recursion, got this one without too much trouble. Code is a bit messy, would be good to move magic numbers out of the code and also separate out the Reactor logic from the ReactionRegistry. Got lucky here with the assumption that there were no cyclic reactions otherwise this would have been more complicated. Could likely be optimized with Memoization but runtime is only 90s or so thus not bothering for now. Wrote the unit test but commented out due to runtime.

## Day 15

Use the IntComputer to create a repair droid to map the craft and find/repair the oxygen generator.

Initially solved without recursion by just randomly moving the droid in different directions and then counting the spaces between the starting position and the oxygen generator.

Extracted the grid printing functionality into a reusable GridPrinter class.

Later used recursion to solve part 1 fully. Part 2 was easy, considering extracting grid helper methods but haven't yet.

## Day 16

Decoding signals using FFT patterns

The trivial answer to part one was fairly easy but unit test runtime is currently 18 seconds and will likely need to be optimized in some way for part 2.

Notable use of Guava's Streams.zip() method but later removed.
> Streams.zip(inputSignal.stream(), pattern.stream(), (a, b) -> a * b).mapToInt(Integer::intValue).sum();

Part 2 was brutal, took about a week, got runtime down to 24 hours only.

## Day 17

Restoring Wi-Fi with vacuum robot

Part one was pretty easy, the reusable grid printing logic was useful here. 

Notable use of Apache Common Collections getCardinalityMap for finding the frequency map of elements in a collection, in this case to determine if the surrounding elements from a given element are all scaffolding.

> CollectionUtils.getCardinalityMap(GridUtility.getSurroundingElements(grid, entry.getKey()).values())