package nl.next35.logic;

import com.ibm.watson.developer_cloud.alchemy.v1.model.*;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ElementTone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.SentenceTone;
import nl.next35.logic.services.AlchemyService;
import nl.next35.logic.services.PersonalityService;
import nl.next35.logic.services.ToneService;

import java.util.List;

/**
 * @author Oscar de Leeuw
 */
public class Main {
    public static void main(String... args) {
        String text = "Hello my name is Chandan and I am a very happy boy.";
        String text2 = "Oil giant Shell is to launch a bid to leave the 10 vast concrete legs of three oil rigs standing in the North Sea for up to 500 years after the platforms have been decommissioned.\n" +
                "\n" +
                "The company’s proposals also include the controversial suggestion that oil mixed with sediment in 42 out of 64 concrete storage cells – each up to 20 metres in diameter and 60 metres high, taller than Nelson’s Column – should remain on the sea bed.\n" +
                "\n" +
                "The decommissioning of the historic Brent field, created during the 1970s boom, represents the Government’s first big decision on what will happen to the main North Sea installations as they stop producing oil.\n" +
                "\n" +
                "The fishing industry said they would prefer \"a return to a clean seabed\" but appreciated the problems in removing the structures.\n" +
                "\n" +
                "Shell’s plan will detail how the company wants to decommission the four Brent oil rigs, three of which rest on concrete legs. Each of those rigs, Brent Bravo, Charlie and Delta, weighs the same as the Empire State Building and is about the same height as the Eiffel Tower.\n" +
                "\n" +
                " \n" +
                "Trump signs executive orders to advance oil pipelines\n" +
                "Under the plan, the platforms would be removed but the concrete legs would remain sticking up out of the sea and the concrete cells would be sealed with any oily sediment left inside.\n" +
                "\n" +
                "Duncan Manning, the business opportunity manager for the Brent Decommissioning project, told The Independent that their modelling of what would happen over the coming centuries suggested the oil would have a “very small” effect on the environment when eventually the storage cells collapse.\n" +
                "\n" +
                "“This sediment is going to remain encased or entombed in these structures for hundreds of years,” he said. “The legs will remain in place for 150 to 250 years. They will eventually degrade at sea level and then slowly crumble down.\n" +
                "\n" +
                "“If they are pushed over in a storm, then they fall on the cells beneath. We got engineers to look at that. \n" +
                "\n" +
                "“While we anticipate because of the weight of the legs, the tops of the cells will crumble, the base of the cell will remain effectively as a bund to hold the sediment in place. The roof of the cells collapses into the cell itself. At that point, you will get some exposure [of the sediment] into the marine environment.”\n" +
                "\n" +
                "Shell believes the cells could remain \"largely upright\" for at least 1,000 years \"despite being punctured and damaged\".\n" +
                "\n" +
                "Mr Manning admitted that what would happen to the structures in 250 years’ time was “not an absolute science”, partly because “we haven’t got reinforced concrete of that age”.\n" +
                "\n" +
                "But he added: “The environmental impact of the oil sediment if and when it is eventually released … is very small. “The option of doing something is completely disproportionate to the environmental impact when eventually that sediment is released.”\n" +
                "\n" +
                "Mr Manning said the cells and legs had been “built to withstand the very worst the North Sea can throw against them” and had not been designed to be refloated.\n" +
                "\n" +
                "However, under the international OSPAR agreement, they are supposed to be removed and so Shell needs permission from the UK Government to leave them in the place.\n" +
                "\n" +
                "Some oil in the storage cells will be removed under Shell's plan, leading behind the more viscous sediment, which is about 25 per cent oil with sand and water making up the rest. A Shell source said the sediment tended to occupy no more than a quarter of the storage cells.\n" +
                "\n" +
                "The company admits that the legs will pose a hazard to shipping, mainly fishing boats, but plans to take steps to ensure fleets are aware of the potential hazard and say that the collision risk is low.\n" +
                "\n" +
                "“There are 25,000 shipwrecks around the UK coastline,” Mr Manning said. “This is really a pin-prick when you think of the total size of the UK continental shelf. This is not a highly trafficked route in the North Sea, it’s not a key route from the UK to Norway.”\n" +
                "\n" +
                "Lang Banks, the director of WWF Scotland, said the amount of oil in the cells and other structures was greater than allowed to be left after decommissioning under international standards.\n" +
                "\n" +
                "“Oil and gas companies operating in the North Sea have a legal, as well as moral, obligation to clean-up their mess,” he said. “Having once pushed the boundaries of science and engineering to secure the oil and gas beneath the seabed, the industry should be pressed once again to do the same when decommissioning.\n" +
                "\n" +
                "“The OSPAR agreement rules are there to make sure the marine environment is protected and those rules should be followed. The main thing preventing this from being done in this particular case is the cost. Shell should do the right thing and remove these potentially polluting materials.”\n" +
                "\n" +
                "However he suggested WWF Scotland would not object to the legs being left in the sea if it was too dangerous to remove them. “The rules already allow for companies to seek permission to leave some material behind – such as the massive concrete legs – where moving it would pose an unacceptable risk to staff or the environment. We accept this principle,” Mr Banks said.\n" +
                "\n" +
                "The Scottish Fishermen's Federation suggested in an emailed statement that it had some sympathy with Shell's hope to leave the legs standing.\n" +
                "\n" +
                "\"The SFF’s desire/policy in relation to oil and gas decommissioning in the UK continental shelf, taking into account current legislation and related guidelines, is as far as possible a return to a clean seabed,\" it said.\n" +
                "\n" +
                "\"We do, however, appreciate that there are safety concerns, technical considerations and cost implications involved in attempting to remove such structures.  \n" +
                "\n" +
                "\"If the legs are to remain and cause an obstruction, as a general principle, the fishing view is that we should be able to see them.\"\n" +
                "\n" +
                "After it has received Shell’s proposals, the Government will hold a public consultation before making its decision.\n" +
                "\n" +
                "A BEIS spokesperson said: “Any decommissioning plan will be carefully considered by the Government, taking into account environmental, safety and cost implications, the impact on other users of the sea and a public consultation.";

        //testAlchemy(text2);
        //testPersonality(text2);
        //testToneService(text2);
    }

    public static void testAlchemy(String text) {
        AlchemyService service = new AlchemyService();
        service.setInput(text);

        Concepts concepts = service.getConcepts();
        DocumentEmotion.Emotion emotion = service.getEmotion();
        Entities entities = service.getEntities();
        Keywords keywords = service.getKeywords();
        Sentiment sentiment = service.getSentiment();

        System.out.println("Break on this line to inspect vars.");
    }

    public static void testPersonality(String text) {
        PersonalityService service = new PersonalityService();
        service.setInput(text);

        Profile profile = service.getProfile();

        System.out.println("Break on this line to inspect vars.");
    }

    public static void testToneService(String text) {
        ToneService service = new ToneService();
        service.setInput(text);

        ElementTone tone = service.getDocumentTone();
        List<SentenceTone> tones = service.getSentenceTone();

        System.out.println("Break on this line to inspect vars.");
    }
}
