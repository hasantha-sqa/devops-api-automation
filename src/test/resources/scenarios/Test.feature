#Feature: Accommodation Search Filters
#
#  #################################### verify Basic Accommodation Search ####################################
#  @severity=blocker @AccomSearchFilters @AccomSearchFilters1 @DevSmokeTest @PipelineQA
#  Scenario Outline: User search for accommodation to verify basic search (Scenario 1 : Basic Accommodation Search) - <scenario> - <requestType>
#    Given user want to test "<requestType>" request for "ACCOM_SEARCH"
#    Given get key controls
#    Examples:
#      | requestType |
#      | TEST        |