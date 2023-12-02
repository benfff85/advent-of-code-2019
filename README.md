# Advent of Code

# 2019

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

Part 2 was a struggle, not sure if there was a systemic way to find the answer. I printed the grid on paper and looked for patterns by hand, took 2-3 days to find the correct main/movement routines.

# 2022

## Day 1

Elves carrying snacks and calculation of calories

Nothing too exciting, lots of summing of Streams, may want to consider making some Util methods in the future.

## Day 2 

Rock Paper Scissors

Good use of ENUMs, used the new Java Switch statement and also cleaned up with a shape relationship map that can go key->value or value->key to determine if one shape beats or loses to another. I should check if there's an existing collection for this in Guava/ApacheCollectionUtils.

Update: turns out both Guava and Apache support the item above, they're called Bidirectional maps.

## Day 3

Common elements in Rucksacks and among elves

Pretty straight forward, used Sets and Apache's CollectionUtils.intersection(a,b) to find common elements across collections.

## Day 4

Find section overlap and subcollection status

Notable use of Apache's IntegerSequence.Range, IterableUtils.toList(), CollectionUtils.containsAny(), CollectionUtils.containsAll().

## Day 5 

Moving of containers between stacks.

Misread the part 1 and actually accidentally solved part 2 first not realizing. Took an embarrassing amount of time to re-read the problem and realize. Nothing to interesting, used the Java libs to reverse a string, used the Spring collection utils to get the last element of a list, notably this method doesn't seem to exist in Apache commons. Cut some corners by hard coding the initial stack state instead of parsing it from input.

## Day 6

Finding marker string in a signal

Unusually easy for day 6, converted to set and got size to find unique elements in a potential marker.

## Day 7

Finding the side of directories and subdirectories

Created custom map, hit issue with false assumption that directory names were globally unique. Calculated directory size with every file addition and oddly ignored the ls command just assuming lists of files/directories took place in the tracked current working directory.

## Day 8

Find visible trees from outside a forrest and from each tree within the forrest.

Not too hard but a bit tedious, not a fan of my solution, didn't break into any other classes. Finished at the Tampa airport. No external libraries used. Refactored out a handful of methods but no classes. Might be worth enhancing grid utils to find elements above, below, to the left and to the right of an element and return a list of them.

## Day 9

Tracing positions visited by the tail end of a rope

A bit challenging, only refactored out the RopeSegment for part 2. Took the opportunity to write some PointUtil methods in addition to GridUtil. Solved using just points, grid is used only for printing in this case. Wasted way too much time thinking part 2 was for 10 rope segments instead of 10 knots (9 rope segments).

## Day 10

Process instructions in cycles and outputting to a screen.

Minor logical errors, did it in the middle of the night in between dealing with Jack's first stomach bug. Minor confusion around pixel and cycle differing by 1. Also a bit odd, I kept cycles in a list and kept the index and cycle number in sync but it doesn't seem like a best practice, may have been better to throw in a map with the key of the cycle number.

## Day 11

Find the most active monkeys that are tossing your items 

Part one was fairly easy, created an abstract class for monkeys and ended up hard coding the inputs. Part 2 I fell for just trying to use a BigInteger but computation would never complete. Admittedly took a bit of a blind guess at reducing the worry level by modding by the greatest common denominator which seemed like a reasonable guess and turned out to be right. Notable use of abstract classes and the stream skip method to get the last two elements in a sorted stream as well as stream reduce to determine the product. 

## Day 12

Find path between source and destination while ascending at most one level of elevation.

This one was a challenge for me, tried to make DFS work for far too long, at least I got some useful utility methods for Grids/Points out of it. Looked into BFS and that was the key. The way I track depth in my BFS implementation is odd, not sure if there is a better way. 

## Day 13

Sorting signals, lists of lists/integers.

Paused for a while before this one. Imported Jackson to help with the input parsing, difficult to deal with collections of different types in Java (Integer / List), ended up just treating them all as Objects and checking types with instanceof. Used the new feature to allow binding to a variable directly in the instance of clause. Also implemented custom compare function for the packets.

## Day 14

Falling sand!

Wrote utility methods for finding the min/max x/y coordinates in a grid and also utility method for finding ranges of points between two points. The grid utils came in handy for this one. Saved some time and confusion by making the Y values negative.

Notable use of Apache Math's int ranges
> toList(new IntegerSequence.Range(min(p1.x, p2.x), max(p1.x, p2.x), 1))

## Day 15

Signals and Beacons, finding covered points per row and the one uncovered point in a 4,000,000 x 4,000,000 grid.

I tried to treat these as individual points in a grid, it worked for part one but was not performant enough for part 2. Switched to tracking the points covered by each sensor as a range from x1-x2. I just used the Apache commons math pair to track the range. It would be nice to find a library for ranges to reduce them and see if they are continuous or not and such. There is IntegerSequence.Range but it looks very basic for just iterating through int ranges with a given stepping.

## Day 16

Finding path between valves to relieve the most pressure.

First part I tried to first do with just recursion but even on the sample input it would never complete. Got fairly lucky that my memoization approach worked off the bat.

The second part was much more challenging, few key things I did was replace the valves with just the valve name in my cache to save space. I realized that when caching based on two points A,B was the same as B,A so I checked the cache twice. Also I needed to add manual pruning to terminate paths that didn't look promising.

Notable use of the LRUMap from ApacheCollections that will fix the size of the map and automatically evict entries based on which was least recently accessed if a new entry is attempting to be added to a full map. Thia was useful to keep memory in check when troubleshooting part 2.

> private final Map<CacheKey, Integer> cache = new LRUMap<>(2500000);

## Day 17

Tetris style falling rocks with gusts of wind. 

The first part wasn't so bad, used the grid I had used before and looped through rock types and gusts of wind. Both of which were cyclic, it was useful to loop with a resetting iterator from Apache.

> ResettableListIterator<RockType> rockTypes = IteratorUtils.loopingListIterator(List.of(DASH, PLUS, ANGLE, LINE, SQUARE));
 
The first section only loops through 2022 rocks and is fairly straight forward. 

Part 2 requires looping through 1000000000000 rocks. Obviously this isn't computable in the same fashion. I created a cache of rocks type, topology and gusts to cache the input/output topology pre/post the rock fall. After manually examining I found a cycle and then simply computed the height gain from the cycle * the number of cycles plus the rocks prior to the cycle plus the rocks post the cycle needed to get to 1000000000000. I have not yet systemically identified the cycle and still relied on manual calculations.

## Day 18 

Calculating the surface area of a 3D lava droplet. 

Solved by creating a 3D list of all the cubes that make up the lava droplet, then just looping through all cubes and seeing if there was another cube immediately in every direction, for any direction where there was not an adjacent cube add one to the surface area. 

This worked fine for part 1, for part 2 we only care about exterior surface area (and not internal cavities). I used a flood fill algorithm for this, the one catch was the droplet was up against the edge of the box. To address this I shifted the droplet coordinates by 1 when initially placing the droplet since its exact location isn't important, just its surface area.

## Day 19

Creating resource generating robots to ultimately collect geodes. 

Part 1 took 30 different robot blueprints and ran them each for 24 steps. Conceptually this wasn't too bad. I made a recursive method that would process each step and then call itself with each options or robot to build (or not build any robots) effectively creating a possibility tree. 

This worked but performance was a concern. I do some simple math to calculate max possible geodes and if its not more than the already identified max I preemptively terminate the branch. Additionally I cache method calls based on resource inventory, robot to build, step and robot inventory. This helps but part one still runs for about 3 minutes.

Part 2 just looks at the first 3 blueprints but runs them for 32 steps. With my existing solution it was able to solve this as well but runtime was delayed further to about 5 minutes. 

I think most of the performance degradation is because I clone my resource and robot maps every time the step method is called but not clear how this can easily be avoided.

# 2023

## Day 1

Finding first and last numeric values in a string. 

2023 started off with a bit of frustration, part one was pretty easy but my solution was delayed due to a off-by-1 error. To get the last digit I just reversed the string and passed it to the same function.

Part two involved resolving text to digits, for example "one" -> 1. I initially fell for the string replacement trap which fails for scenarios like "twone", instead iterated through each character checking for numeric string values and constructed a new string.

## Day 2

Playing game of unknown number of colored cubes in a bag.

Nothing too notable today, solved with a simple loop max observed values of cubes of each color. Then looped through the list, filtered games not possible and summed. Part 2 just required one additional power() method that multiplied the max observed cube count together. Similarly looped through games, mapped to the power value and summed.