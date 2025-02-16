package project.src.java.util;

import project.src.java.util.customExceptions.*;
import project.src.java.util.executionSettings.JSON.ExecutionSettingsData.ConditionalEquationMux.SettingsJsonCEM;
import project.src.java.util.executionSettings.JSON.ExecutionSettingsData.ExecutionSettings;
import project.src.java.util.executionSettings.JSON.ExecutionSettingsData.Table.SettingsJsonT;

public class InputJsonValidator {
    public void execute(ExecutionSettings executionsSettings) {
        try {
            if (executionsSettings.estimatorsGenerationPolicy == null){
                throw new JsonValidationException("input file error: field not found 'regenerate_estimators': string");
            }
            if (executionsSettings.executionsSettings == null){
                throw new JsonValidationException("input file error: field not found 'execution_settings': array");
            }

            for (int index = 0; index < executionsSettings.executionsSettings.size(); index++) {
                validateBasicFields(executionsSettings.executionsSettings.get(index));

                if (executionsSettings.executionsSettings.get(index) instanceof SettingsJsonT){
                    validateTableFields(
                        (SettingsJsonT)
                        executionsSettings.executionsSettings.get(index));
                }
                if (executionsSettings.executionsSettings.get(index) instanceof SettingsJsonCEM){
                    validateConditionalEquationMuxFields(
                        (SettingsJsonCEM)
                        executionsSettings.executionsSettings.get(index));
                }
            }
        } catch (JsonValidationException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void validateBasicFields(project.src.java.util.executionSettings.JSON.ExecutionSettingsData.Settings settings)
            throws JsonValidationException {
        if (settings.approach == null){
            throw new JsonValidationException("input file error: field not found 'approach': string");
        }
        if (settings.dataset == null){
            throw new JsonValidationException("input file error: field not found 'approach': string");
        }
        if (settings.precision == null){
            throw new JsonValidationException("input file error: field not found 'precision': string");
        }
        if (settings.target == null){
            throw new JsonValidationException("input file error: field not found 'target': string");
        }
        if (settings.platform == null){
            throw new JsonValidationException("input file error: field not found 'platform': object");
        }
        if (settings.platform.inputBitwidth == null){
            throw new JsonValidationException("input file error: field not found 'input_bitwidth': integer");
        }
        if (settings.trainingParameters == null){
            throw new JsonValidationException("input file error: field not found 'training_parameters': object");
        }
        if (settings.trainingParameters.estimatorsQuantity == null){
            throw new JsonValidationException("input file error: field not found 'estimators_quantity': integer");
        }
        if (settings.trainingParameters.trainingPercent == null){
            throw new JsonValidationException("input file error: field not found 'training_percent': float");
        }
        if (settings.trainingParameters.maxDepth == null){
            throw new JsonValidationException("input file error: field not found 'max_depth': integer");
        }
    }

    private void validateTableFields(SettingsJsonT settings) throws JsonValidationException {
        if (settings.inferenceParameters == null) {
            throw new JsonValidationException("input file error: field not found 'inference_parameters': object");
        }
        if (settings.inferenceParameters.fieldsBitwidth == null) {
            throw new JsonValidationException("input file error: field not found 'fields_bitwidth': object");
        }
        if (settings.inferenceParameters.fieldsBitwidth.comparedColumn == null) {
            throw new JsonValidationException("input file error: field not found 'compared_column': integer");
        }
        if (settings.inferenceParameters.fieldsBitwidth.index == null) {
            throw new JsonValidationException("input file error: field not found 'index': integer");
        }
        if (settings.inferenceParameters.fieldsBitwidth.comparedValue == null) {
            throw new JsonValidationException("input file error: field not found 'compared_value': integer");
        }
    }

    private void validateConditionalEquationMuxFields(SettingsJsonCEM settings) throws JsonValidationException {
        if (settings.inferenceParameters == null) {
            throw new JsonValidationException("input file error: field not found 'inference_parameters': object");
        }
        if (settings.inferenceParameters.precision == null) {
            throw new JsonValidationException("input file error: field not found 'comparedValue': integer");
        }
    }
}

