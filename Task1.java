public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

    // FIX: Initialize result list to avoid NullPointerException
    List<LoanAccount> result = new ArrayList<>();

    // FIX: Guard against null accounts list
    if (accounts == null) {
        return result;
    }

    for (LoanAccount account : accounts) {

        // FIX: dueDate may be null for restructured accounts
        if (account.getDueDate() != null &&
            account.getDueDate().before(new Date())) {

            // FIX: Exclude zero outstanding balance accounts
            if (account.getOutstandingBalance() > 0) {
                result.add(account);
            }
        }
    }
    return result;
}