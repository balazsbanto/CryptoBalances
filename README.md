# CryptoBalances
A simple app to check your crypto balances. The intro screen will show your wallet address. The second screen will show your ERC20 tokens balance. 

Note: the UI is very basic.

Intro Screen:

* Just displays the given Ethereum wallet address.
* Shows a button to navigate to the 2nd screen.
* Note that the Ethereum address will be hardcoded in the app; The user won’t be able to change it.

ERC20 screen:
* Shows a search box where user can search for ERC20 tokens
* As the user types in the box, show the balance of the token the user is looking
for (no dollar value needed). This is the number of tokens that belong to the
hardcoded address.
* The balance is in green text if positive, red when zero.
