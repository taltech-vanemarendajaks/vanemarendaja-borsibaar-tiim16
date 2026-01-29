EX01

PR:
1) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/pull/14
2) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/pull/13
3) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/pull/9
4) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/pull/11

Artur:
1) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/4
2) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/10
3) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/12

Kevin: 
1) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/4
2) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/12
3) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/issues/5

Marina:
1) https://github.com/taltech-vanemarendajaks/vanemarendaja-borsibaar-tiim16/pull/11

**Konfliktid:**

Issue #4 puhul tegi Artur seni kuni jäi kinni. Mõni aeg hiljem ma vaatasin ise sinna peale ja tegin täiesti iseseisvalt 
töötava lahenduse. Arturil oli ühe haru all tegelikult 2 issue asjad ja pärast kui oli vaja teha teise issue (#10) jaoks
PR, tegime konfliktide lahendamist nii, et tema harusse tuleks minu muudatused seoses Issue #4 pealt aga #10 jääks tema 
muudatused. Siin kasutasime tegelikult Intellij sisseehitatud võimalusi, et saaks täpselt valida, millist koodi osa võtta
kas "paremalt" või "vasakult" (ours/theirs).

Teine konflikt oli giti käsurealt git pull --no-rebase -X ours, kus Artur tegi täienduse Issue #12 peal. Lugedes hoiatati,
et ours/theirs võib olla vastupidi pullimisel ja siis panin ours aga tegelikult oleks pidanud panema theirs. Tegin muudatuse
pärast eraldi commitiga.