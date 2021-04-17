<font size="1"> CoronavirusSimulator</font> 

Simulator of Covid-19 spread written in Java. This simulator simulates the spread of viruses in various scenarios, using a circle to represent a human.

<font size="1">Simulation</font>

The simulation begins with one person infected on each side, and the first infected begins infecting others. From being infected, a person can go to the hospital or recover on their own. Those hospitalized have a higher chance of dying, and the hospital only has a certain capacity before no more people can enter. The simulation on the right side has a fraction of the population practicing social distancing, meaning they will stay in place.

<font size="1">Colors</font>

Green - recovered
Red - infected
Pink - hospitalized recovered
Blue - healthy
Gray - dead
Note: Horizontal black line is the hospital capacity

<font size="1">Control variables</font>

Graph scaling - Changes how the graph is scaled vertically so they fit on-screen.
Tickrate - Delay on timer (aka tickrate).
People - Number of people per simulation.
Record rate - Graph will record data every time this number of ticks pass.
Immobile people - Number of people who are practicing social distancing in the distanced simulation.
Hospital capacity - Hospital capacity per simulation- it is guaranteed that no more than this many people will be hospitalized.
