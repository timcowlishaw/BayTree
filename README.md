# Bay Tree

## A nascent library for bayesian statistics, probability and Bayes nets in Scala

This is a learning exercise intended to help me get my head around Bayes networks and various other statistical concepts. I'll be adding to it periodically as I learn more, Hopefully at some point it will be of practical use to both me and others. Features implemented so far:

 * Random Variables
    * Create a random variable over a set of objects, with associated probabilities
    * Easily create random variables conforming to certain well known distributions over sets of objects (Currently only the uniform distribution, but I'm planning more)
    * Get the probability of any object under a certain distribution
    * Get the probability of all objects satisfying a certain predicate
    * Create the joint distribution of two random variables

Here's a list of additional features I have planned:
  * Conditional distributions
  * Bayesian inference
  * Bayes networks
  * Markov Chains and HMMs
  * Probably lots more.

Comments and feedback gladly received, although you probably don't want to be using this in your own work just yet, as while I've been rigarous about testing the features I've implemented, interfaces may change as I continue working. I'll probably not accept pull-requests either, as the main point of the project is to work out how to do these things myself, however, if anything here is useful to you, please fork!

