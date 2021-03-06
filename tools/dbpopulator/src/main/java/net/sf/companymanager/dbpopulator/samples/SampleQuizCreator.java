package net.sf.companymanager.dbpopulator.samples;

import net.sf.companymanager.dto.QuestionDTO;
import net.sf.companymanager.dto.SectionDTO;
import net.sf.companymanager.dto.QuestionOptionDTO;
import net.sf.companymanager.dto.QuestionnaireDefinitionDTO;
import net.sf.companymanager.dto.embeddables.QuestionLanguageSettingsDTO;
import net.sf.companymanager.dto.embeddables.QuestionOptionLanguageSettingsDTO;
import net.sf.companymanager.dto.embeddables.QuestionnaireDefinitionLanguageSettingsDTO;
import net.sf.companymanager.dto.embeddables.SectionLanguageSettingsDTO;
import net.sf.companymanager.dto.support.TranslationDTO;
import net.sf.companymanager.facades.QuestionnaireDefinitionEditorFacade;
import net.sf.companymanager.types.Language;
import net.sf.companymanager.types.QuestionType;
import net.sf.companymanager.types.RenderingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleQuizCreator {

    @Autowired
    private QuestionnaireDefinitionEditorFacade questionnaireDefinitionEditorFacade;

    public QuestionnaireDefinitionDTO create() {
        QuestionnaireDefinitionDTO questionnaireDefinition = QuestionnaireDefinitionDTO.with().language(Language.EN)
                .questionnairLanguageSettingsStart().title("European general knowledge quiz")
                .description("How much do you know about Europe? Answer to this questions and let's find out!")
                .welcomeText("Thank you for taking the time to participate in this questionnaire.")
                .questionnairLanguageSettingsEnd().renderingMode(RenderingMode.SECTION_BY_SECTION).build();
        questionnaireDefinition = questionnaireDefinitionEditorFacade.save(questionnaireDefinition);

        TranslationDTO<QuestionnaireDefinitionDTO, QuestionnaireDefinitionLanguageSettingsDTO> questionnairTranslation = new TranslationDTO<>();
        questionnairTranslation.setLanguage(Language.ES);
        questionnairTranslation.setLanguageSettings(QuestionnaireDefinitionLanguageSettingsDTO.with()
                .title("Test de conocimiento general sobre Europa")
                .description("¿Cuánto sabes sobre Europa? Contesta a estas preguntas y averígualo!")
                .welcomeText("Gracias por participar en este cuestionario").build());
        questionnairTranslation.setTranslatedEntity(questionnaireDefinition);

        questionnaireDefinitionEditorFacade.saveQuestionnaireTranslation(questionnairTranslation);

        // Page 1
        SectionDTO section1 = SectionDTO.with().language(Language.EN).randomizationEnabled(false)
                .pageLanguageSettingsStart().title("European Capitals").pageLanguageSettingsEnd().build();

        questionnaireDefinition.addSection(section1);
        questionnaireDefinition = questionnaireDefinitionEditorFacade.save(questionnaireDefinition);

        section1 = questionnaireDefinition.getLastSectionDTO();

        TranslationDTO<SectionDTO, SectionLanguageSettingsDTO> sectionTranslation = new TranslationDTO<>();
        sectionTranslation.setLanguage(Language.ES);
        sectionTranslation.setLanguageSettings(SectionLanguageSettingsDTO.with()
                .title("Capitales Europeas").build());
        sectionTranslation.setTranslatedEntity(section1);
        questionnaireDefinitionEditorFacade.saveSectionTranslation(sectionTranslation);

        // Page 2
        SectionDTO section2 = SectionDTO.with().language(Language.EN).randomizationEnabled(false)
                .pageLanguageSettingsStart().title("European Union").pageLanguageSettingsEnd().build();

        questionnaireDefinition.addSection(section2);
        questionnaireDefinition = questionnaireDefinitionEditorFacade.save(questionnaireDefinition);
        section2 = questionnaireDefinition.getLastSectionDTO();

        sectionTranslation = new TranslationDTO<>();
        sectionTranslation.setLanguage(Language.ES);
        sectionTranslation.setLanguageSettings(SectionLanguageSettingsDTO.with().title("Unión Europea")
                .build());
        sectionTranslation.setTranslatedEntity(section2);
        questionnaireDefinitionEditorFacade.saveSectionTranslation(sectionTranslation);

        // Page 3
        SectionDTO section3 = SectionDTO.with().language(Language.EN).randomizationEnabled(false)
                .pageLanguageSettingsStart().title("European History").pageLanguageSettingsEnd().build();

        questionnaireDefinition.addSection(section3);
        questionnaireDefinition = questionnaireDefinitionEditorFacade.save(questionnaireDefinition);
        section3 = questionnaireDefinition.getLastSectionDTO();

        sectionTranslation = new TranslationDTO<>();
        sectionTranslation.setLanguage(Language.ES);
        sectionTranslation.setLanguageSettings(SectionLanguageSettingsDTO.with().title("Sociedad").build());
        sectionTranslation.setTranslatedEntity(section3);
        questionnaireDefinitionEditorFacade.saveSectionTranslation(sectionTranslation);

        // 1 Single Textbox
        QuestionDTO question = QuestionDTO.with().type(QuestionType.S).language(Language.EN).code("Q1")
                .languageSettingsStart().title("What is the capital of Malta?").languageSettingsEnd().required(true)
                .build();
        section1.addQuestion(question);
        section1 = questionnaireDefinitionEditorFacade.save(section1);
        question = section1.getLastQuestionDTO();

        TranslationDTO<QuestionDTO, QuestionLanguageSettingsDTO> questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuál es la capital de Malta?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        // 2 List (Radio) Multiple Choice (Only One QuestionOption)
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).code("Q2").languageSettingsStart()
                .title("Which the country has as capital Copenhague?").languageSettingsEnd().required(true).build();

        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Norway").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Sweden").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Denmark").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Iceland").build());

        section1.addQuestion(question);
        section1 = questionnaireDefinitionEditorFacade.save(section1);
        question = section1.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Qué pais tiene como capital Copenague?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        TranslationDTO<QuestionOptionDTO, QuestionOptionLanguageSettingsDTO> questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation
                .setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Noruega").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Suecia").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Dinamarca")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Islandia")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 3 Numeric
        question = QuestionDTO.with().type(QuestionType.N).language(Language.EN).code("Q3").languageSettingsStart()
                .title("How many European capitals were founded by Romans?").languageSettingsEnd().required(true)
                .build();

        section1.addQuestion(question);
        section1 = questionnaireDefinitionEditorFacade.save(section1);

        question = section1.getLastQuestionDTO();
        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuantas capitales europeas fueron fundadas por los romanos?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        // 4. List checkbox - Multiple_Answers
        question = QuestionDTO.with().type(QuestionType.M).code("Q4").language(Language.EN).languageSettingsStart()
                .title("Which ones of these European capital are near by a river? Choose all that apply.")
                .languageSettingsEnd().required(false).build();
        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Vatican City")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Roma").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Helsinki").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Viena").build());

        section1.addQuestion(question);
        section1 = questionnaireDefinitionEditorFacade.save(section1);

        question = section1.getLastQuestionDTO();
        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuantas de estas capitales Europeas estan al pie de un río?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with()
                .title("Ciudad del Vaticano").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Roma").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Helsinki")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Viena").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 5 Single Textbox
        question = QuestionDTO.with().type(QuestionType.S).language(Language.EN).code("Q5").languageSettingsStart()
                .title("What does EU stand for??").languageSettingsEnd().required(true).build();
        section2.addQuestion(question);
        section2 = questionnaireDefinitionEditorFacade.save(section2);
        question = section2.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with().title("¿Que significa UE?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        // 6 List (Radio) Multiple Choice (Only One QuestionOption)
        question = QuestionDTO
                .with()
                .type(QuestionType.L)
                .language(Language.EN)
                .code("Q6")
                .languageSettingsStart()
                .title("Where was the treaty signed that created the European economic community - forerunner of the EU?")
                .languageSettingsEnd().required(true).build();

        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Westphalia")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Rome").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Brussels").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Versailles")
                .build());

        section2.addQuestion(question);
        section2 = questionnaireDefinitionEditorFacade.save(section2);
        question = section2.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Dónde fue firmado el trado de Comunidad Económica Europea, precedesor de Unión Europea?")
                .build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Westphalia")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Roma").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Bruselas")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Islandia")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 7. List checkbox - Multiple_Answers
        question = QuestionDTO.with().type(QuestionType.M).code("Q7").language(Language.EN).languageSettingsStart()
                .title("Which ones of these European countries belongs to Euro Zone? Choose all that apply.")
                .languageSettingsEnd().required(false).build();
        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Norway").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Poland").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Macedonia").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Chipre").build());

        section2.addQuestion(question);
        section2 = questionnaireDefinitionEditorFacade.save(section2);

        question = section2.getLastQuestionDTO();
        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuál de estos países europeos pertenecen a la zona euro?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation
                .setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Noruega").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation
                .setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Polonia").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Macedonia")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Chipre").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 8 Numeric
        question = QuestionDTO.with().type(QuestionType.N).language(Language.EN).code("Q8").languageSettingsStart()
                .title("How many finnish marc do you need to get an euro?").languageSettingsEnd().required(true)
                .build();

        section2.addQuestion(question);
        section2 = questionnaireDefinitionEditorFacade.save(section2);

        question = section2.getLastQuestionDTO();
        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuantos marcos finlandeses necesitas para tener un euro?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        // 9 List (Radio) Multiple Choice (Only One QuestionOption)
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).code("Q9").languageSettingsStart()
                .title("Where was Christopher columbus was born?").languageSettingsEnd().required(true).build();

        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Barcelona").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Lisboa").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Genoa").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Roma").build());

        section3.addQuestion(question);
        section3 = questionnaireDefinitionEditorFacade.save(section3);
        question = section3.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Dónde nació Cristobal Colón?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Barcelona")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Lisboa").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Genova").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Roma").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 10 List (Radio) Multiple Choice (Only One QuestionOption)
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).code("Q10").languageSettingsStart()
                .title("What was the large Eurpean Empire in century 16th ?").languageSettingsEnd().required(true)
                .build();

        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Portuguese Empire")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("British Empire")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN).title("Spanish Empire")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Dutch Empire")
                .build());

        section3.addQuestion(question);
        section3 = questionnaireDefinitionEditorFacade.save(section3);
        question = section3.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cuál fue el imperio más grande en el siglo XVI?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with()
                .title("Imperio Portugues").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with()
                .title("Imperio Británico").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Imperio Español")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with()
                .title("Imperio Holandés").build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        // 11 Single Textbox
        question = QuestionDTO.with().type(QuestionType.S).language(Language.EN).code("Q11").languageSettingsStart()
                .title("Which nationality has the first european that to get Congo?").languageSettingsEnd()
                .required(true).build();
        section3.addQuestion(question);
        section3 = questionnaireDefinitionEditorFacade.save(section3);
        question = section3.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Qué nacionalidad tenía el primer Europeo que llegó a Congo?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        // 12 List (Radio) Multiple Choice (Only One QuestionOption)
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).code("Q12").languageSettingsStart()
                .title("Which of the men caused more casualities in Europe?").languageSettingsEnd().required(true)
                .build();

        question.addQuestionOption(QuestionOptionDTO.with().code("O1").language(Language.EN).title("Adolph Hitler")
                .build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O2").language(Language.EN).title("Napoleon").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O3").language(Language.EN)
                .title("Philip 2th King of Spain").build());
        question.addQuestionOption(QuestionOptionDTO.with().code("O4").language(Language.EN).title("Julius Caesar")
                .build());

        section3.addQuestion(question);
        section3 = questionnaireDefinitionEditorFacade.save(section3);
        question = section3.getLastQuestionDTO();

        questionTranslation = new TranslationDTO<>();
        questionTranslation.setLanguage(Language.ES);
        questionTranslation.setLanguageSettings(QuestionLanguageSettingsDTO.with()
                .title("¿Cual de estos hombres causó más bajas en Europa?").build());
        questionTranslation.setTranslatedEntity(question);
        questionnaireDefinitionEditorFacade.saveQuestionTranslation(questionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Adolfo Hitler")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(0));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Napoleón")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(1));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Felipe II")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(2));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        questionOptionTranslation = new TranslationDTO<>();
        questionOptionTranslation.setLanguage(Language.ES);
        questionOptionTranslation.setLanguageSettings(QuestionOptionLanguageSettingsDTO.with().title("Julio Cesar")
                .build());
        questionOptionTranslation.setTranslatedEntity(question.getQuestionOptions().get(3));
        questionnaireDefinitionEditorFacade.saveQuestionOptionTranslation(questionOptionTranslation);

        return questionnaireDefinition;
    }

}
