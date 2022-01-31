# Python

## TODO

- How does python handle packages for larger projects / testing?
- How do I bundle up my scripts into an app?

## Unit testing

// todo : review pytest, testing tool

- can be run from the command line with:
	- `python -m <test_file>
	- `python -m unittest discover`
		- include `-v` to set verbose output
	- `python -m test`

- To setup a typical test directory structure:
	- include `__init__.py` with the source files under test
	- create `test` directory as same level as source files
	- within `test` create `test_<file_name>.py` test files
	- within `test` create `__init__.py`, to allow importing packages
	- _see ctci/arrays.py for a simple example
