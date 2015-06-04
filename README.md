Arms Race Outline:
    Coming soon

Competitive:
    Match Starts:
        Player stats are reset
        Round starts:
            Players are teleported to coords defined in config
            Players are frozen
            10 second asynchronous countdown is started:
                Players buy items from chest GUI
            10 second countdown ends:
                Players unfrozen 
            2 minute asynchronous countdown is started:
                EventListeners respond to deaths, ect
            2 minute Timer ends
        Round ends:
            Players get money for winning losing:
                $3250 by a time win or team elimination
                $3500 by winning through a bomb defuse or a bomb detonation
                The losing team receives:
                    $1400 after losing the first round
                    $1900 after losing 2 rounds in a row
                    $2400 after losing 3 rounds in a row
                    $2900 after losing 4 rounds in a row
                    $3400 after losing 5 or more rounds in a row
                    If Terrorists were able to plant the bomb but lose the round, all Terrorists receive a $800 bonus additionally to the values above
                    Defusing or planting the bomb awards $300 for the individual
                Teamkilling will result in a penalty of -$300
            If not halftime, new round
            else switch teams, reset money, new round
